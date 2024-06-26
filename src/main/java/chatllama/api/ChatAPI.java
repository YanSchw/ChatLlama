package chatllama.api;

import chatllama.config.Config;
import chatllama.data.entity.Chat;
import chatllama.data.entity.ChatMessage;
import chatllama.data.service.ChatMessageService;
import chatllama.data.service.ChatService;
import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.OllamaStreamHandler;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;
import io.github.amithkoujalgi.ollama4j.core.utils.PromptBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatAPI {

    @GetMapping(value = "/api/prompt/{chatid}/{prompt}", produces = "application/json")
    public String prompt(@PathVariable String chatid, @PathVariable String prompt) {
        Chat chat = ChatService.getInstance().getOrCreateNewChat(chatid);

        ChatMessage yourMessage = new ChatMessage(prompt);
        yourMessage.setModelMessage(false);
        chat.getMessages().add(yourMessage);
        ChatMessageService.getRepository().save(yourMessage);

        ChatMessage responseMessage = new ChatMessage();
        responseMessage.setPending(true);
        responseMessage.setModelMessage(true);
        chat.getMessages().add(responseMessage);
        ChatMessageService.getRepository().save(responseMessage);
        ChatService.getRepository().save(chat);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String host = Config.getOllamaServerHostURL();
                    OllamaAPI ollamaAPI = new OllamaAPI(host);
                    ollamaAPI.setRequestTimeoutSeconds(120);

                    String model = OllamaModelType.LLAMA3;

                    PromptBuilder promptBuilder = new PromptBuilder().addLine(prompt);

                    OptionsBuilder optionsBuilder = new OptionsBuilder();
                    OllamaStreamHandler ollamaStreamHandler = new OllamaStreamHandler() {
                        @Override
                        public void accept(String str) {
                            responseMessage.setMessage(str);
                            ChatMessageService.getRepository().save(responseMessage);
                        }
                    };

                    OllamaResult response = ollamaAPI.generate(model, promptBuilder.build(), optionsBuilder.build(), ollamaStreamHandler);
                    responseMessage.setMessage(response.getResponse());
                    responseMessage.setPending(false);
                    ChatMessageService.getRepository().save(responseMessage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();


        return chat.toString();
    }

    @GetMapping(value = "/api/fetchChat/{chatid}", produces = "application/json")
    public String fetchChat(@PathVariable String chatid) {
        Chat chat = ChatService.getInstance().getOrCreateNewChat(chatid);

        return chat.toString();
    }

}

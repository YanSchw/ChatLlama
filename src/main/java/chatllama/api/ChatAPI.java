package chatllama.api;

import chatllama.config.Config;
import chatllama.data.entity.Chat;
import chatllama.data.entity.ChatMessage;
import chatllama.data.service.ChatMessageService;
import chatllama.data.service.ChatService;
import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.OllamaStreamHandler;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatMessage;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatMessageRole;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatRequestModel;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;
import io.github.amithkoujalgi.ollama4j.core.utils.PromptBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatAPI {

    @GetMapping(value = "/api/prompt/{chatid}", produces = "application/json")
    public String prompt(@PathVariable String chatid, @RequestHeader("prompt") String prompt) {
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

                    //OllamaResult response = ollamaAPI.generate(model, promptBuilder.build(), optionsBuilder.build(), ollamaStreamHandler);

                    List<OllamaChatMessage> ollamaChatMessages = new ArrayList<>();
                    for (ChatMessage It : chat.getMessages()) {
                        OllamaChatMessage ollamaChatMessage = new OllamaChatMessage();
                        ollamaChatMessage.setContent(It.getMessage());
                        ollamaChatMessage.setRole(It.isModelMessage() ? OllamaChatMessageRole.SYSTEM : OllamaChatMessageRole.USER);
                        ollamaChatMessages.add(ollamaChatMessage);
                    }
                    OllamaChatResult result = ollamaAPI.chat(new OllamaChatRequestModel(model, ollamaChatMessages), ollamaStreamHandler);
                    responseMessage.setMessage(result.getResponse());
                    responseMessage.setPending(false);
                    ChatMessageService.getRepository().save(responseMessage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();

        generateTitle(chat);

        return chat.toString();
    }

    public void generateTitle(Chat chat) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String host = Config.getOllamaServerHostURL();
                    OllamaAPI ollamaAPI = new OllamaAPI(host);
                    ollamaAPI.setRequestTimeoutSeconds(120);

                    String model = OllamaModelType.LLAMA3;

                    List<OllamaChatMessage> ollamaChatMessages = new ArrayList<>();
                    for (ChatMessage It : chat.getMessages()) {
                        OllamaChatMessage ollamaChatMessage = new OllamaChatMessage();
                        ollamaChatMessage.setContent(It.getMessage());
                        ollamaChatMessage.setRole(It.isModelMessage() ? OllamaChatMessageRole.SYSTEM : OllamaChatMessageRole.USER);
                        ollamaChatMessages.add(ollamaChatMessage);
                    }

                    OllamaChatMessage ollamaChatMessage = new OllamaChatMessage();
                    ollamaChatMessage.setContent("Sumarize this Chat within 5 words");
                    ollamaChatMessage.setRole(OllamaChatMessageRole.USER);
                    ollamaChatMessages.add(ollamaChatMessage);

                    OllamaChatResult result = ollamaAPI.chat(new OllamaChatRequestModel(model, ollamaChatMessages));
                    chat.setTitle(result.getResponse());
                    ChatService.getRepository().save(chat);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
    }

    @GetMapping(value = "/api/fetchChat/{chatid}", produces = "application/json")
    public String fetchChat(@PathVariable String chatid) {
        Chat chat = ChatService.getInstance().getOrCreateNewChat(chatid);

        return chat.toString();
    }

    @GetMapping(value = "/api/allChats", produces = "application/json")
    public String allChats() {
        List<Chat> chats = ChatService.getRepository().getAllChats();
        JSONObject json = new JSONObject();
        List<JSONObject> chatsJSON = new ArrayList<>();
        for (Chat chat : chats) {
            JSONObject jsonChat = new JSONObject();
            jsonChat.put("chatid", chat.getId());
            jsonChat.put("title", chat.getTitle());
            chatsJSON.add(jsonChat);
        }
        json.put("chats", chatsJSON);
        return json.toString();
    }

}

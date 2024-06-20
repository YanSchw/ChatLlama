package chatllama.models;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.OllamaStreamHandler;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;
import io.github.amithkoujalgi.ollama4j.core.utils.PromptBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Llama3 {

    @GetMapping(value = "/llama3/{prompt}", produces = "text/plain")
    @ResponseBody
    public String promptLlama3(@PathVariable String prompt) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String host = "http://host.docker.internal:11434/";
                    OllamaAPI ollamaAPI = new OllamaAPI(host);
                    ollamaAPI.setRequestTimeoutSeconds(120);

                    String model = OllamaModelType.LLAMA3;

                    PromptBuilder promptBuilder = new PromptBuilder().addLine(prompt);

                    OptionsBuilder optionsBuilder = new OptionsBuilder();
                    OllamaStreamHandler ollamaStreamHandler = new OllamaStreamHandler() {
                        @Override
                        public void accept(String s) {
                            System.out.println(s);
                        }
                    };

                    OllamaResult response = ollamaAPI.generate(model, promptBuilder.build(), optionsBuilder.build(), ollamaStreamHandler);
                    System.out.println(response.getResponse());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();


        return "";
    }

}

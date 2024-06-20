package chatllama;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initApplication() {
        String host = "http://host.docker.internal:11434/";

        try {
            OllamaAPI ollamaAPI = new OllamaAPI(host);
            ollamaAPI.setVerbose(true);
            boolean isOllamaServerReachable = ollamaAPI.ping();
            LoggerFactory.getLogger(DemoApplication.class).info("Is Ollama server alive: " + isOllamaServerReachable);

            LoggerFactory.getLogger(DemoApplication.class).info("Pulling Model: Llama3");
            ollamaAPI.pullModel(OllamaModelType.LLAMA3);
            LoggerFactory.getLogger(DemoApplication.class).info("Model Llama3 ready.");
        } catch (Exception e) {
            LoggerFactory.getLogger(DemoApplication.class).error("Ollama server is unavailable!");
            throw new RuntimeException();
        }

    }

}

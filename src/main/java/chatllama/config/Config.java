package chatllama.config;

public class Config {

    public static String ollamaServerHostname = "http://localhost";
    public static Integer ollamaServerPort = 11434;

    public static String getOllamaServerHostURL() {
        return ollamaServerHostname + ":" + ollamaServerPort;
    }

}

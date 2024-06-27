package chatllama.config;

import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class Config {

    public static Integer chatLlamaPort = 8080;

    public static String getChatLlamaPort() {
        return String.valueOf(chatLlamaPort);
    }

    public static String ollamaServerHostname = "http://localhost";
    public static Integer ollamaServerPort = 11434;

    public static String getOllamaServerHostURL() {
        return ollamaServerHostname + ":" + ollamaServerPort;
    }

    public static String mySqlHostname = "jdbc:mysql://localhost";
    public static Integer mySqlPort = 3306;
    public static String mySqlUsername = "root";
    public static String mySqlPassword = "1234";

    public static String getMySqlHostURL() {
        return mySqlHostname + ":" + mySqlPort + "/chatllama?createDatabaseIfNotExist=true";
    }
    public static String getMySqlUsername() {
        return mySqlUsername;
    }
    public static String getMySqlPassword() {
        return mySqlPassword;
    }

    public static void initialize(String[] args) {
        var iterator = Arrays.stream(args).iterator();

        try {
            for (String str = iterator.next(); str != null; str = iterator.next()) {
                if (str.equals("-CHATLLAMA_PORT")) {
                    chatLlamaPort = Integer.valueOf(iterator.next());
                }

                if (str.equals("-OLLAMA_HOSTNAME")) {
                    ollamaServerHostname = iterator.next();
                }
                if (str.equals("-OLLAMA_PORT")) {
                    ollamaServerPort = Integer.valueOf(iterator.next());
                }

                if (str.equals("-MYSQL_HOSTNAME")) {
                    mySqlHostname = iterator.next();
                }
                if (str.equals("-MYSQL_PORT")) {
                    mySqlPort = Integer.valueOf(iterator.next());
                }
                if (str.equals("-MYSQL_USERNAME")) {
                    mySqlUsername = iterator.next();
                }
                if (str.equals("-MYSQL_PASSWORD")) {
                    mySqlPassword = iterator.next();
                }
            }
        } catch (Exception ignored) { }

        LoggerFactory.getLogger(Config.class).info("CHATLLAMA_PORT: " + chatLlamaPort);
        LoggerFactory.getLogger(Config.class).info("OLLAMA_HOSTNAME: " + ollamaServerHostname);
        LoggerFactory.getLogger(Config.class).info("OLLAMA_PORT: " + ollamaServerPort);
        LoggerFactory.getLogger(Config.class).info("MYSQL_HOSTNAME: " + mySqlHostname);
        LoggerFactory.getLogger(Config.class).info("MYSQL_PORT: " + mySqlPort);
        LoggerFactory.getLogger(Config.class).info("MYSQL_USERNAME: " + mySqlUsername);
        LoggerFactory.getLogger(Config.class).info("MYSQL_PASSWORD: " + mySqlPassword);
    }

}

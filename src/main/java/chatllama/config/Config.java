package chatllama.config;

public class Config {

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

}

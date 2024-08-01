package chatllama.data.entity;

import jakarta.persistence.*;

import java.security.SecureRandom;
import java.util.Base64;

@Entity
public class SessionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String token;

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private static final int TOKEN_LENGTH = 32; // Length in bytes

    public static String generateTokenString() {
        SecureRandom secureRandom = new SecureRandom();

        // Generate a random byte array
        byte[] randomBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(randomBytes);

        // Encode the byte array into a Base64 string
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        return token;
    }
}

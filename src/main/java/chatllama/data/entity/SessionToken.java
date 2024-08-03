package chatllama.data.entity;

import chatllama.data.service.SessionTokenService;
import chatllama.data.service.UserService;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class SessionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String token;

    LocalDateTime expirationTimestamp = LocalDateTime.now().plusHours(24);

    Long userId = null;

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(LocalDateTime expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return UserService.getInstance().getUserById(getUserId());
    }

    public void setUser(User user) {
        this.userId = user.getId();
    }

    public boolean isValid() {
        return expirationTimestamp.isAfter(LocalDateTime.now());
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

    public static SessionToken createTokenForUser(User user) {
        SessionToken sessionToken = new SessionToken();
        sessionToken.setUser(user);
        sessionToken.setToken(generateTokenString());
        SessionTokenService.getRepository().save(sessionToken);

        return sessionToken;
    }
}

package chatllama.components;

import chatllama.data.entity.SessionToken;
import chatllama.data.repository.SessionTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExpiredSessionTokenCleaner {

    private static final Logger logger = LoggerFactory.getLogger(ExpiredSessionTokenCleaner.class);

    @Autowired
    private SessionTokenRepository sessionTokenRepository;

    @Scheduled(fixedRate = 60_000)
    public void cleanExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<SessionToken> expiredTokens = sessionTokenRepository.findByExpirationTimestampBefore(now);

        if (!expiredTokens.isEmpty()) {
            sessionTokenRepository.deleteAll(expiredTokens);
            logger.info("Deleted " + expiredTokens.size() + " expired session tokens.");
        }
    }
}
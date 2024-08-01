package chatllama.data.service;

import chatllama.data.entity.SessionToken;
import chatllama.data.repository.SessionTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionTokenService {

    private static SessionTokenService instance;

    private final SessionTokenRepository sessionTokenRepository;

    public SessionTokenService(SessionTokenRepository sessionTokenRepository) {
        instance = this;
        this.sessionTokenRepository = sessionTokenRepository;
    }

    public static SessionTokenService getInstance() {
        return instance;
    }

    public static SessionTokenRepository getRepository() {
        return getInstance().sessionTokenRepository;
    }

    public SessionToken getByToken(String token) {
        List<SessionToken> list = getRepository().getSessionTokenByTokenString(token);
        return list.isEmpty() ? null : list.get(0);
    }

}

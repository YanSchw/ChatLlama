package chatllama.data.service;

import chatllama.data.repository.SessionTokenRepository;
import org.springframework.stereotype.Service;

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

}

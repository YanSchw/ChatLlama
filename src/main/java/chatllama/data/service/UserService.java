package chatllama.data.service;

import chatllama.data.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static UserService instance;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        instance = this;
        this.userRepository = userRepository;
    }

    public static UserService getInstance() {
        return instance;
    }

    public static UserRepository getRepository() {
        return getInstance().userRepository;
    }

}

package chatllama.data.service;

import chatllama.data.entity.User;
import chatllama.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUserById(Long userId) {
        List<User> list = getRepository().getUserById(userId);
        return list.isEmpty() ? null : list.get(0);
    }
}

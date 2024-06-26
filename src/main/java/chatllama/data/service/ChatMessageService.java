package chatllama.data.service;

import chatllama.data.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    private static ChatMessageService instance;

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        instance = this;
        this.chatMessageRepository = chatMessageRepository;
    }

    public static ChatMessageService getInstance() {
        return instance;
    }

    public static ChatMessageRepository getRepository() {
        return getInstance().chatMessageRepository;
    }

}

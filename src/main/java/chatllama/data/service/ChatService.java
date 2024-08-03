package chatllama.data.service;

import chatllama.data.entity.Chat;
import chatllama.data.entity.User;
import chatllama.data.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private static ChatService instance;

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        instance = this;
        this.chatRepository = chatRepository;
    }

    public static ChatService getInstance() {
        return instance;
    }

    public static ChatRepository getRepository() {
        return getInstance().chatRepository;
    }

    public Chat getOrCreateNewChat(String chatid, User user) {
        if (chatid.equals("new")) {
            Chat newChat = new Chat();
            newChat.setUser(user);
            getRepository().save(newChat);
            return newChat;
        }
        return getRepository().getChatById(Long.valueOf(chatid)).get(0);
    }
}

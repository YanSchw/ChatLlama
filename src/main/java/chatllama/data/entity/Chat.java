package chatllama.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany
    List<ChatMessage> messages;

    public Chat() {
        messages = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public boolean isPending() {
        for (ChatMessage message : messages) {
            if (message.isPending()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("chatid", getId());
        json.put("isPending", isPending());
        List<JSONObject> messagesJSON = new ArrayList<>();
        for (ChatMessage It : getMessages()) {
            messagesJSON.add(It.toJSON());
        }
        json.put("messages", messagesJSON);
        return json.toString();
    }
}

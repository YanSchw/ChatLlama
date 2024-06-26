package chatllama.data.entity;

import jakarta.persistence.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String message;

    @Transient
    private transient boolean isPending = false;

    public ChatMessage() {
        message = "";
    }

    public ChatMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isPending() {
        return this.isPending;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("message", getMessage());
        return json.toString();
    }

}

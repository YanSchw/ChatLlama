package chatllama.data.entity;

import jakarta.persistence.*;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long getId() {
        return id;
    }

}

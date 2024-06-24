package chatllama.data.repository;

import chatllama.data.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("Select c from Chat c")
    List<Chat> getAllChats();

    @Query("Select c from Chat c where c.id = :Id")
    List<Chat> getChatById(@Param("Id") Long Id);

}

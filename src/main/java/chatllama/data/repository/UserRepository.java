package chatllama.data.repository;

import chatllama.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u")
    List<User> getAllUsers();

    @Query("Select u from User u where u.id = :Id")
    List<User> getUserById(@Param("Id") Long Id);

    @Query("Select u from User u where u.username = :Username and u.passwordHash = :PasswordHash")
    List<User> getUserByNameAndPassword(@Param("Username") String username, @Param("PasswordHash") String passwordHash);

}

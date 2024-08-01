package chatllama.data.repository;

import chatllama.data.entity.SessionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long> {

    @Query("Select st from SessionToken st")
    List<SessionToken> getAllSessionTokens();

    @Query("Select st from SessionToken st where st.id = :Id")
    List<SessionToken> getSessionTokenById(@Param("Id") Long Id);

    @Query("Select st from SessionToken st where st.token = :Token")
    List<SessionToken> getSessionTokenByTokenString(@Param("Token") String token);

    List<SessionToken> findByExpirationTimestampBefore(LocalDateTime now);

}

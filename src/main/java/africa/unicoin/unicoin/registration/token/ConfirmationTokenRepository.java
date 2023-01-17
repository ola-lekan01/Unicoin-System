package africa.unicoin.unicoin.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    void deleteConfirmationTokensByExpiredAtBefore(LocalDateTime currentTime);

    @Modifying
    @Query("UPDATE ConfirmationToken confirmationToken " +
            "SET confirmationToken.confirmedAt = ?1 " +
            "WHERE confirmationToken.confirmedAt = ?2 "
    )
    void setConfirmedAt(LocalDateTime currentTime, String Token);
}

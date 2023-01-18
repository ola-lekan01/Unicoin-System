package africa.unicoin.unicoin.registration.token;

import africa.unicoin.unicoin.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull String token;
    private @NotNull LocalDateTime createdAt;
    private @NotNull LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @ManyToOne @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User savedUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = savedUser;
    }
}
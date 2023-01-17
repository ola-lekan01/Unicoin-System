package africa.unicoin.unicoin.registration.token;


import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenService {
void saveConfirmationToken(ConfirmationToken confirmToken);
void getConfirmationToken(String confirmToken);
void deleteExpiredConfirmationToken();
ConfirmationToken confirmAccessToken(String confirmationToken);
void setConfirmedAt(String token);
}

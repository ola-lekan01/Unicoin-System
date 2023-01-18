package africa.unicoin.unicoin.registration.token;

import africa.unicoin.unicoin.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@EnableScheduling
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository tokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmToken) {
        tokenRepository.save(confirmToken);
    }

    @Override
    public void getConfirmationToken(String confirmToken) {
        tokenRepository.findByToken(confirmToken);
    }

    @Override
    @Scheduled(cron = "0 00 00 * * *")
    public void deleteExpiredConfirmationToken() {
        System.out.println("Deleted");
        tokenRepository.deleteConfirmationTokensByExpiredAtBefore(LocalDateTime.now());
    }

    @Override
    public ConfirmationToken confirmAccessToken(String confirmationToken) {
        return tokenRepository.findByToken(confirmationToken).orElseThrow(() -> new RegistrationException("Token Does Not Exist"));
    }

    @Override
    public void setConfirmedAt(String token) {
        tokenRepository.setConfirmedAt(LocalDateTime.now(), token);
    }
}

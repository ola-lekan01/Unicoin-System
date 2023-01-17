package africa.unicoin.unicoin.user;

import africa.unicoin.unicoin.registration.token.ConfirmationToken;
import africa.unicoin.unicoin.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ConfirmationTokenService tokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConfirmationTokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public String createAccount(User user) {
        var savedUser = userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                savedUser
        );

        tokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress) {
        return userRepository.findByEmailAddressIgnoreCase(emailAddress);
    }
}

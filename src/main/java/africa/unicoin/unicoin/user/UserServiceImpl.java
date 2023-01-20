package africa.unicoin.unicoin.user;

import africa.unicoin.unicoin.exception.RegistrationException;
import africa.unicoin.unicoin.exception.UserException;
import africa.unicoin.unicoin.user.dtos.LoginRequest;
import africa.unicoin.unicoin.registration.token.ConfirmationToken;
import africa.unicoin.unicoin.registration.token.ConfirmationTokenService;
import africa.unicoin.unicoin.user.dtos.PasswordResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
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
        return generateToken(savedUser);
    }

    private String generateToken(User savedUser) {
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
    public String generateToken(String email){
        var foundUser =  findUserByEmailAddressIgnoreCase(email)
                .orElseThrow(()-> new RegistrationException(email + " does not exist."));
        return generateToken(saveUser(foundUser));
    }

    @Override
    public String changePassword(PasswordResetRequest passwordReset) {
        var foundUser = findUserByEmailAddressIgnoreCase(passwordReset.getEmail())
                .orElseThrow(() -> new UserException(passwordReset.getEmail() + " does not exist"));
        System.out.println(foundUser.getPassword());
        System.out.println(passwordReset.getEmail());
        System.out.println(passwordReset.getOldPassword());
        System.out.println(passwordReset.getNewPassword());
        if(!Objects.equals(foundUser.getPassword(), passwordReset.getOldPassword())) throw new UserException("Password Mismatch");
        userRepository.setPassword(passwordReset.getEmail(), passwordReset.getNewPassword());
        return "Password Reset Successful";
    }

    @Override
    public Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress) {
        return userRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public boolean enableUser(String email) {
        userRepository.setIsDisabledToTrue(email);
        return true;
    }

    @Override
    public User saveUser(User foundUser) {
        return userRepository.save(foundUser);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        var foundUser = findUserByEmailAddressIgnoreCase(loginRequest.getEmail())
                .orElseThrow(()-> new UserException(String.format("%s email does not exist", loginRequest.getEmail())));

        if(!foundUser.getPassword().equals(loginRequest.getPassword())) throw new UserException("Invalid email or Password");
        if(foundUser.isDisabled()) throw new UserException("User is not enabled, please verify your email");

        return "Logged in Successful";
    }
}
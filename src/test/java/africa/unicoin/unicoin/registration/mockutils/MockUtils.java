package africa.unicoin.unicoin.registration.mockutils;

import africa.unicoin.unicoin.email.EmailSender;
import africa.unicoin.unicoin.registration.token.ConfirmationTokenRepository;
import africa.unicoin.unicoin.registration.token.ConfirmationTokenServiceImpl;
import africa.unicoin.unicoin.user.UserRepository;
import africa.unicoin.unicoin.user.UserServiceImpl;

import static org.mockito.Mockito.mock;


public class MockUtils {
    public static final UserRepository userRepositoryMock = mock(UserRepository.class);
    public static final ConfirmationTokenRepository tokenRepositoryMock = mock(ConfirmationTokenRepository.class);
    public static final EmailSender emailSenderMock = mock(EmailSender.class);


    public static UserServiceImpl userServiceMock(){
        return new UserServiceImpl(userRepositoryMock, confirmationTokenServiceMock());
    }

    public static ConfirmationTokenServiceImpl confirmationTokenServiceMock(){
        return new ConfirmationTokenServiceImpl(tokenRepositoryMock);
    }
}
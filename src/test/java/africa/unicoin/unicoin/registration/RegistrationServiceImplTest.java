package africa.unicoin.unicoin.registration;

import africa.unicoin.unicoin.registration.dtos.ConfirmationTokenRequest;
import africa.unicoin.unicoin.registration.dtos.RegistrationRequest;
import africa.unicoin.unicoin.registration.mockutils.MockUtils;
import africa.unicoin.unicoin.registration.token.ConfirmationToken;
import africa.unicoin.unicoin.user.User;
import africa.unicoin.unicoin.user.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static africa.unicoin.unicoin.registration.mockutils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {

    private final UserServiceImpl userServiceMock = spy(MockUtils.userServiceMock());
    private final RegistrationService registrationService = new RegistrationServiceImpl(
            userServiceMock,
            MockUtils.emailSenderMock,
            MockUtils.confirmationTokenServiceMock()
    );

    @Test
    void register() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "James",
                "Lakes",
                "lakes_lakes123@gmail.com",
                "12345"
        );
        doReturn("f1884964-a3d0-4a9a-ba95-d6c35b4f58db").when(userServiceMock).createAccount(any(User.class));
        assertEquals(registrationService.register(registrationRequest), "f1884964-a3d0-4a9a-ba95-d6c35b4f58db");
    }

    @Test void confirmToken(){
        ConfirmationTokenRequest tokenRequest = ConfirmationTokenRequest.builder()
                .token("f1884964-a3d0-4a9a-ba95-d6c35b4f58db")
                .email("lakes_lakes123@gmail.com")
                .build();

        Optional<User> user = Optional.of(new User());
        doReturn(user).when(userRepositoryMock).findByEmailAddressIgnoreCase("lakes_lakes123@gmail.com");

        Optional<ConfirmationToken> confirmationToken = Optional.of(new ConfirmationToken());
        doReturn(true).when(userServiceMock.enableUser("lakes_lakes123@gmail.com"));
        doReturn(true).when(confirmationTokenServiceMock().setConfirmedAt("f1884964-a3d0-4a9a-ba95-d6c35b4f58db"));
        doReturn(confirmationToken).when(confirmationTokenServiceMock().confirmAccessToken("f1884964-a3d0-4a9a-ba95-d6c35b4f58db"));
        assertEquals(registrationService.confirmToken(tokenRequest),"Confirmed!!!");
    }

    @Test
    public void testResendToken() throws MessagingException {
        Optional<User> user = Optional.of(new User());
        doReturn(user).when(userRepositoryMock).findByEmailAddressIgnoreCase(any(String.class));

        doReturn("85656674-1488-4d64-aca6-e78ff6d757fc")
                .when(userServiceMock).generateToken(any(String.class));

        assertEquals("Token sent", registrationService.resendToken("12374@gmail.com"));
    }
}
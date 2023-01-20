package africa.unicoin.unicoin.registration;

import africa.unicoin.unicoin.registration.dtos.ConfirmationTokenRequest;
import africa.unicoin.unicoin.registration.dtos.RegistrationRequest;
import africa.unicoin.unicoin.registration.dtos.ResendTokenRequest;
import africa.unicoin.unicoin.user.dtos.PasswordResetRequest;
import africa.unicoin.unicoin.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    public ResponseEntity<?> register (@RequestBody RegistrationRequest registrationRequest,
                                       HttpServletRequest httpServletRequest) throws MessagingException {

        var createdUser = registrationService.register(registrationRequest);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(createdUser).
                build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmToken (@RequestBody ConfirmationTokenRequest confirmationTokenRequest,
                                           HttpServletRequest httpServletRequest){

        var confirmedToken = registrationService.confirmToken(confirmationTokenRequest);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.OK.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(confirmedToken).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendToken (@RequestBody ResendTokenRequest tokenRequest,
                                          HttpServletRequest httpServletRequest) throws MessagingException {

        var confirmedToken = registrationService.resendToken(tokenRequest.getEmail());

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.OK.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(confirmedToken).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest resetRequest, HttpServletRequest httpServletRequest) throws MessagingException {

        var resetResponse = registrationService.resetPassword(resetRequest.getEmail());

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.OK.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(resetResponse).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
package africa.unicoin.unicoin.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean disabled;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public User(String emailAddress, String firstName, String lastName, String password, UserRole userRole) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
    }
}

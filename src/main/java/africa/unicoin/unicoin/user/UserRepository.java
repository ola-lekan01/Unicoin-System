package africa.unicoin.unicoin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddressIgnoreCase(String email);

    @Modifying
    @Query("UPDATE User user " +
            "SET user.disabled = true " +
            "WHERE user.emailAddress = ?1 "
    )
    void setIsDisabledToFalse(String email);
}

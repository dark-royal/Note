package africa.semicolon.note.data.repositories;

import africa.semicolon.note.data.models.User;
import africa.semicolon.note.data.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}

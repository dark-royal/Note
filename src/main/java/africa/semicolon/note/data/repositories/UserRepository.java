package africa.semicolon.note.data.repositories;

import africa.semicolon.note.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByEmail(String email);
}

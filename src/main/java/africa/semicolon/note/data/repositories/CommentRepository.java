package africa.semicolon.note.data.repositories;

import africa.semicolon.note.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

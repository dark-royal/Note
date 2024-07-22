package africa.semicolon.note.data.repositories;

import africa.semicolon.note.data.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}

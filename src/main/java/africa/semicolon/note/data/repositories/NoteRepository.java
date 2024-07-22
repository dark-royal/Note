package africa.semicolon.note.data.repositories;

import africa.semicolon.note.data.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}

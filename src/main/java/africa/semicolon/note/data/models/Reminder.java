package africa.semicolon.note.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@Table(name = "notifications")
public class Reminder {
    @Id
    private Long id;
    private String content;
    private LocalDateTime dateSent;
    @ManyToOne
    private User user;

}

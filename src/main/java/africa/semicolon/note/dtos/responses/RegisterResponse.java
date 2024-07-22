package africa.semicolon.note.dtos.responses;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String message;
    @Id
    private Long id;
    private String username;
    private String email;
}

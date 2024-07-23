package africa.semicolon.note.dtos.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}

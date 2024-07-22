package africa.semicolon.note;

import africa.semicolon.note.dtos.requests.RegisterRequest;
import africa.semicolon.note.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplementationTest {

    private UserService userService;

    @Test
    public void testCreateAccount(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("praise");
    }

}

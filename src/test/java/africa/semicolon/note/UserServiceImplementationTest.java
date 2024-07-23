package africa.semicolon.note;

import africa.semicolon.note.dtos.requests.RegisterRequest;
import africa.semicolon.note.dtos.responses.RegisterResponse;
import africa.semicolon.note.exceptions.UserExistAlreadyException;
import africa.semicolon.note.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceImplementationTest {
    @Autowired
    private UserService userService;



    @Test
    public void testCreateAccount() throws UserExistAlreadyException {
        RegisterRequest request = createAccount("praiseoyewole50@gmail.com","praisey","florence");
        RegisterResponse response = userService.createAccount(request);
        assertThat(response.getMessage()).contains("Registration successfully");
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatDuplicateEmailFoundThrow_UserExistAlreadyException() throws UserExistAlreadyException {
        RegisterRequest request = createAccount("praiseoyewole59@gmail1.com","israel","real");
        RegisterResponse response = userService.createAccount(request);
        assertThat(response).isNotNull();
        assertThrows(UserExistAlreadyException.class,()->userService.createAccount(request));
    }

    private RegisterRequest createAccount(String email, String username,String password){
        return RegisterRequest.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

}

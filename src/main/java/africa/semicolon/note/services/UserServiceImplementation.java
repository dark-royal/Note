package africa.semicolon.note.services;

import africa.semicolon.note.data.models.User;
import africa.semicolon.note.data.repositories.UserRepository;
import africa.semicolon.note.dtos.requests.LoginRequest;
import africa.semicolon.note.dtos.requests.RegisterRequest;
import africa.semicolon.note.dtos.responses.LoginResponse;
import africa.semicolon.note.dtos.responses.RegisterResponse;
import africa.semicolon.note.exceptions.UserExistAlreadyException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final VerificationServiceImplementation verificationTokenService;

    public UserServiceImplementation(PasswordEncoder passwordEncoder, ModelMapper modelMapper, VerificationServiceImplementation verificationTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public RegisterResponse createAccount(RegisterRequest registerRequest) throws UserExistAlreadyException {
        User user = modelMapper.map(registerRequest,User.class);
        passwordEncoder.encode(registerRequest.getPassword());
        user.setEnable(false);

        Optional<User> saved = Optional.of(user);
        saved.ifPresent( u ->{
            try{
                String token = UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(),token);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        user = userRepository.save(user);
        var response = modelMapper.map(user,RegisterResponse.class);
        response.setMessage("Registration successfully");
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}

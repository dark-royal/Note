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
    private final EmailService emailService;
    private final VerificationServiceImplementation verificationTokenService;


    public UserServiceImplementation(PasswordEncoder passwordEncoder, ModelMapper modelMapper, EmailService emailService, VerificationServiceImplementation verificationTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.verificationTokenService = verificationTokenService;
    }

    @Override
    public RegisterResponse createAccount(RegisterRequest registerRequest) throws UserExistAlreadyException {
        validateRegistration(registerRequest.getEmail());
        User user = modelMapper.map(registerRequest,User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnable(false);

        userRepository.save(user);


        Optional<User> saved = Optional.of(user);
//        saved.ifPresent( u ->{
//            try{
//                String token = UUID.randomUUID().toString();
//                verificationTokenService.save(saved.get(),token);
//                emailService.sendHtmlMail(u);
//            }catch (Exception e){
//                    throw new RuntimeException(e.getMessage());
//            }
//        });

//        user = userRepository.save(user);
        var response = modelMapper.map(user,RegisterResponse.class);
        response.setMessage("Registration successfully");
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }



    private void validateRegistration(String email) throws UserExistAlreadyException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new UserExistAlreadyException(String.format("%s exists already, enter another email address",email));
        }
 }
}

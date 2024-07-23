package africa.semicolon.note.services;

import africa.semicolon.note.dtos.requests.LoginRequest;
import africa.semicolon.note.dtos.requests.RegisterRequest;
import africa.semicolon.note.dtos.responses.LoginResponse;
import africa.semicolon.note.dtos.responses.RegisterResponse;
import africa.semicolon.note.exceptions.UserExistAlreadyException;

public interface UserService {

    RegisterResponse createAccount(RegisterRequest registerRequest) throws UserExistAlreadyException;

    LoginResponse login(LoginRequest loginRequest);

}

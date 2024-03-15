package com.crudsecuritywebflux.security.infrastructure.services;


import com.crudsecuritywebflux.security.api.config.jwt.JwtProvider;
import com.crudsecuritywebflux.security.api.model.request.CreateUserRequest;
import com.crudsecuritywebflux.security.api.model.request.LoginRequest;
import com.crudsecuritywebflux.security.api.model.response.LoginResponse;
import com.crudsecuritywebflux.security.core.entities.UserAccount;
import com.crudsecuritywebflux.security.core.enums.Role;
import com.crudsecuritywebflux.security.core.repositories.UserAccountRepository;
import com.crudsecuritywebflux.security.infrastructure.interfaces.IUserService;
import com.crudsecuritywebflux.shared.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserAccountRepository userAccountRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        return userAccountRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(jwtProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid credentials")));
    }

    @Override
    public Mono<UserAccount> createUser(CreateUserRequest request) {
        UserAccount userAccount = UserAccount.builder()
                .username(request.getUsername())
                .names(request.getName())
                .lastnames(request.getLastname())
                .email(request.getEmail())
                .roles(Role.ROLE_USER.name())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Mono<Boolean> userExists = userAccountRepository.findByUsernameOrEmail(userAccount.getUsername(), userAccount.getEmail()).hasElement();

        return userExists.flatMap(exists -> exists
                    ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "User already exists"))
                    :  userAccountRepository.save(userAccount));
    }

}

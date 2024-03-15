package com.crudsecuritywebflux.security.infrastructure.interfaces;

import com.crudsecuritywebflux.security.api.model.request.CreateUserRequest;
import com.crudsecuritywebflux.security.api.model.request.LoginRequest;
import com.crudsecuritywebflux.security.api.model.response.LoginResponse;
import com.crudsecuritywebflux.security.core.entities.UserAccount;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<LoginResponse> login(LoginRequest request);
    Mono<UserAccount> createUser(CreateUserRequest request);
}

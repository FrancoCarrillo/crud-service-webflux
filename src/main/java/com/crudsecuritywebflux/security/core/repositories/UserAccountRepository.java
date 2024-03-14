package com.crudsecuritywebflux.security.core.repositories;

import com.crudsecuritywebflux.security.core.entities.UserAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, Long> {
}

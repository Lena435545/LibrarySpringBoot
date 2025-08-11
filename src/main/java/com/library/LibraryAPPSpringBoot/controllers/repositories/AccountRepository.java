package com.library.LibraryAPPSpringBoot.controllers.repositories;

import com.library.LibraryAPPSpringBoot.controllers.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUserName(String username);
}

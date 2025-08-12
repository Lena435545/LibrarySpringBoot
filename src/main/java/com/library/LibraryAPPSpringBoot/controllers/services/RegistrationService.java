package com.library.LibraryAPPSpringBoot.controllers.services;

import com.library.LibraryAPPSpringBoot.controllers.models.Account;
import com.library.LibraryAPPSpringBoot.controllers.models.Role;
import com.library.LibraryAPPSpringBoot.controllers.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        accountRepository.save(account);
    }
}

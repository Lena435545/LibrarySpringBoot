package com.library.LibraryAPPSpringBoot.controllers.services;

import com.library.LibraryAPPSpringBoot.controllers.models.Account;
import com.library.LibraryAPPSpringBoot.controllers.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AccountRepository accountRepository;

    public RegistrationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void register(Account account){
        accountRepository.save(account);
    }
}

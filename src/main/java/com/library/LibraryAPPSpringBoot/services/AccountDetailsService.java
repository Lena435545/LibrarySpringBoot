package com.library.LibraryAPPSpringBoot.services;

import com.library.LibraryAPPSpringBoot.models.Account;
import com.library.LibraryAPPSpringBoot.repositories.AccountRepository;
import com.library.LibraryAPPSpringBoot.security.AccountDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);

        if(account.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new AccountDetails(account.get());
    }
}
package com.example.service;

//import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.*;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    //
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
        if(account.getUsername().isBlank())
            throw new InvalidRegistrationException();

        if(account.getPassword().length() < 4)
            throw new InvalidRegistrationException();

        if(accountRepository.existsByUsername(account.getUsername()))
            throw new DuplicateUsernameException();
        
        return accountRepository.save(account);
    }

    public Account verifyLoginCredentials(Account account){
        Optional<Account> accountOptional = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        
        return accountOptional.orElseThrow(InvalidLoginCredentialsException::new);
    }
}

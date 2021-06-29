package com.sda.jz75_security_template.service;

import com.sda.jz75_security_template.exception.InvalidRegisterData;
import com.sda.jz75_security_template.model.Account;
import com.sda.jz75_security_template.model.CreateAccountRequest;
import com.sda.jz75_security_template.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }

    public boolean register(CreateAccountRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new InvalidRegisterData("Passwords do not match!");
        }

        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if(accountOptional.isPresent()){
            throw new InvalidRegisterData("Account with given username already exists!");
        }

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        accountRepository.save(account);
        return true;
    }
}

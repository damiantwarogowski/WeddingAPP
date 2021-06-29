package com.sda.jz75_security_template.service;

import com.sda.jz75_security_template.model.Account;
import com.sda.jz75_security_template.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }
}

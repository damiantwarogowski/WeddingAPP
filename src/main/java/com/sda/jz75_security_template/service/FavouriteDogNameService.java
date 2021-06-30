package com.sda.jz75_security_template.service;

import com.sda.jz75_security_template.model.Account;
import com.sda.jz75_security_template.model.FavouriteDogName;
import com.sda.jz75_security_template.repository.AccountRepository;
import com.sda.jz75_security_template.repository.FavouriteDogNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouriteDogNameService {
    private final FavouriteDogNameRepository favouriteDogNameRepository;

    public List<FavouriteDogName> favouriteDogNameList(Account account){
        return favouriteDogNameRepository.findAllByAccount(account);
    }
}

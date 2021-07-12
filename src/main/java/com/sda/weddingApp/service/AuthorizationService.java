package com.sda.weddingApp.service;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.repository.AccountRepository;
import com.sda.weddingApp.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username (parametr) to jest nazwa użytkownika wpisana w formularzu
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {

            return optionalAccount.get();
        }
        Optional<Person> optionalPerson = personRepository.findByEmail(username);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            return person.getAccount();
        }
        throw new UsernameNotFoundException("Can't find " + username);


    }
}

package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByAccount(Account account); //mail albo id?


    Optional<Person> findByEmail(String email);
}




package com.sda.jz75_security_template.repository;

import com.sda.jz75_security_template.model.Account;
import com.sda.jz75_security_template.model.FavouriteDogName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteDogNameRepository extends JpaRepository<FavouriteDogName, Long> {
    List<FavouriteDogName> findAllByAccount(Account account);
}

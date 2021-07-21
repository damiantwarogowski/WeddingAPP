package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CoupleRepository extends JpaRepository<Couple, Long> {

}




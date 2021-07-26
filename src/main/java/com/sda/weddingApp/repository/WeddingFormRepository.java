package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeddingFormRepository extends JpaRepository<Wedding, Long> {
}

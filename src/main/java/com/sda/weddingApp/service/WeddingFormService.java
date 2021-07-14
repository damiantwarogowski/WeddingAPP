package com.sda.weddingApp.service;

import com.sda.weddingApp.model.Wedding;
import com.sda.weddingApp.repository.WeddingFormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeddingFormService {
    private final WeddingFormRepository weddingFormRepository;
}

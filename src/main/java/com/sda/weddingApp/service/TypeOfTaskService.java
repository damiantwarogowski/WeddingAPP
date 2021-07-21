package com.sda.weddingApp.service;

import com.sda.weddingApp.model.TypeOfTask;
import com.sda.weddingApp.repository.TypeofTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeOfTaskService {
    private final TypeofTaskRepository typeofTaskRepository;

    public List<TypeOfTask> findAll() {
        return typeofTaskRepository.findAll();
    }
}

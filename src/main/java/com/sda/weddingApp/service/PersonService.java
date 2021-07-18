package com.sda.weddingApp.service;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.model.dto.PersonDto;
import com.sda.weddingApp.model.mapper.PersonMapper;
import com.sda.weddingApp.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<Person> personList(Account account) {
        return personRepository.findAllByAccount(account);
    }


    public void addPerson(Person person) {
        if (!isValid(person)) {
            return;
        }

        // jeśli poprawny, dodaj do bazy
        personRepository.save(person);
    }

    public boolean addPerson(PersonDto personDto) {
        Person person = personMapper.getPersonFromDto(personDto);
        if (!isValid(person)) {
            return false;
        }

        // jeśli poprawny, dodaj do bazy
        personRepository.save(person);
        return true;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public List<PersonDto> getAllPersonInfo() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::getDtoFromPerson)
                .collect(Collectors.toList());
    }

    private boolean isValid(Person person) {
        return Objects.nonNull(person) &&
                Objects.nonNull(person.getFirstName()) &&
                Objects.nonNull(person.getLastName());
    }

    public void removePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonWithId(Long id) {
        return personRepository.findById(id);
    }

    public boolean removePersonWithId(Long id) {
        Optional<Person> personOptional = getPersonWithId(id);
        if (personOptional.isPresent()) {
            removePersonWithId(id);
            return true;
        }
        return false;
    }

    public Optional<PersonDto> update(Long id, PersonDto personDto) {
        Optional<Person> personOptional = getPersonWithId(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();

            personMapper.update(personDto, person);
            return Optional.of(personMapper.getDtoFromPerson(personRepository.save(person)));
        }
        return Optional.empty();
    }
}


package com.sda.weddingApp.controller.rest;

import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.model.dto.PersonDto;
import com.sda.weddingApp.model.dto.ResponseMessage;
import com.sda.weddingApp.model.mapper.PersonMapper;
import com.sda.weddingApp.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class RestPersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @ApiOperation(value = "Returns full list of persons.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List successfully returned."),
            @ApiResponse(code = 403, message = "Access to this call is forbidden."),
            @ApiResponse(code = 404, message = "Call with such method does not exist."),
            @ApiResponse(code = 415, message = "List can't be retrieved and is not supported."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. Error should be investigated.")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<ResponseMessage<List<PersonDto>>> getPersonList() {

        return ResponseEntity.ok(new ResponseMessage<>(personService.getAllPersonInfo(), "Response OK!"));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<ResponseMessage<PersonDto>> getPersonWithId(@PathVariable Long personId) {
        // Response Entity - klasa java - Ramka HTTP
        //          -- status (code)
        //          -- body
        Optional<Person> personOptional = personService.getPersonWithId(personId);
        if (personOptional.isPresent()) {   // jeśli znaleźliśmy studenta
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage<>(personMapper.getDtoFromPerson(personOptional.get()), "Response OK!"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(null, "Person with given ID does not exist!"));
        }
    }

    // dodawanie
    @PostMapping()
    public ResponseEntity<ResponseMessage<PersonDto>> addPerson(@RequestBody PersonDto dtoOfPersonToAdd) {
        boolean result = personService.addPerson(dtoOfPersonToAdd);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // usuwanie
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> removePerson(@PathVariable Long id) {
        boolean result = personService.removePersonWithId(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // GET - pobieramy
    // POST - wstawiamy
    // PUT - zastępujemy
    // PATCH - aktualizujemy (części)
    // DELETE - usuwanie
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessage<PersonDto>> updatePerson(@PathVariable Long id,
                                                                     @RequestBody PersonDto personDto) {
        Optional<PersonDto> resultOpt = personService.update(id, personDto);
        if (resultOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage<>(resultOpt.get(), "Person updated!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(null, "Person does not exist!"));
        }
    }

}


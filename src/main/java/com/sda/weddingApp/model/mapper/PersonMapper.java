package com.sda.weddingApp.model.mapper;


import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.model.dto.PersonDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings(value = {
            @Mapping(target = "name", source = "firstName"),
            @Mapping(target = "surname", source = "lastName"),
            @Mapping(target = "email", source = "email")
    })
    PersonDto getDtoFromPerson(Person person);

    @Mappings(value = {
            @Mapping(source = "name", target = "firstName"),
            @Mapping(source = "surname", target = "lastName"),
            @Mapping(source = "email", target = "email")
    })
    Person getPersonFromDto(PersonDto personDto);

    // aktualizacja rekordu
    @InheritInverseConfiguration(name = "getDtoFromPerson")
    void update(PersonDto dto, @MappingTarget Person entity);
}



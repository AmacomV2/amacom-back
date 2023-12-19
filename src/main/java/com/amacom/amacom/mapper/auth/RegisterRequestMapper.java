package com.amacom.amacom.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonDTO;
import com.amacom.amacom.dto.auth.NewUserRequestDTO;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import com.amacom.amacom.model.Person;
import com.amacom.amacom.model.auth.NewUserRequest;

@Mapper
public interface RegisterRequestMapper {

    RegisterRequestMapper INSTANCE = Mappers.getMapper(RegisterRequestMapper.class);

    // DTO TO ENTITY
    NewUserRequest toNewUserRequest(NewUserRequestDTO registerRequestDTO);

    // DTO TO ENTITY
    NewUserRequest toNewUserRequest(RegisterRequestDTO registerRequestDTO);

    Person toPerson(RegisterRequestDTO personDTO);

    PersonDTO toPersonDTO(RegisterRequestDTO request);

}

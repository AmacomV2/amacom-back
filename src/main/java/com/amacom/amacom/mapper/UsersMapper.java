package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.UsersDTO;
import com.amacom.amacom.model.auth.User;

@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "person.id", source = "personId")
    @Mapping(target = "rol.id", source = "idRol")
    User toUserDTO(UsersDTO usuarioDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "idRol", source = "rol.id")
    UsersDTO toUsersDTO(User usuario);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("username", "username");
        keysToSort.put("email", "email");
        keysToSort.put("createdAt", "createdAt");
        return keysToSort;
    }

}

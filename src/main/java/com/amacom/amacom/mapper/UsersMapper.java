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

    User toUserDTO(UsersDTO usuarioDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "idRol", source = "rol.id")
    UsersDTO toUsersDTO(User usuario);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("username", "username");
        clavesToSort.put("email", "email");
        clavesToSort.put("createdAt", "createdAt");
        return clavesToSort;
    }

}

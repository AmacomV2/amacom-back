package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.model.PersonBabys;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface PersonBabysMapper {

    PersonBabysMapper INSTANCE = Mappers.getMapper(PersonBabysMapper.class);

    PersonBabys toPersonBabys(PersonBabysDTO personBabysDTO);


    @Mapping(target = "idPadre", source = "padre.id")
    @Mapping(target = "idBebe", source = "bebe.id")
    PersonBabysDTO toPersonBabysDTO(PersonBabys personBabys);


    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }


}

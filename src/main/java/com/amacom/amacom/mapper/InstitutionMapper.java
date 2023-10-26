package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.model.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface InstitutionMapper {

    InstitutionMapper INSTANCE = Mappers.getMapper(InstitutionMapper.class);

    Institution toInstitution(InstitutionDTO institutionDTO);

    @Mapping(target = "idTipoInstitucion", source = "tipoInstitucion.id")
    InstitutionDTO toInstitutionDTO(Institution institution);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombre", "nombre");
        clavesToSort.put("descripcion", "descripcion");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }


}

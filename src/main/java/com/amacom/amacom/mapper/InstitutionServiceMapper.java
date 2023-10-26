package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InstitutionServiceDTO;
import com.amacom.amacom.model.InstitutionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface InstitutionServiceMapper {

    InstitutionServiceMapper INSTANCE = Mappers.getMapper(InstitutionServiceMapper.class);

    InstitutionService toInstitutionService(InstitutionServiceDTO institutionServiceDTO);

    @Mapping(target = "idUsuario", source = "usuario.id")
    @Mapping(target = "idServices", source = "services.id")
    @Mapping(target = "idInstitution", source = "institution.id")
    InstitutionServiceDTO toInstitutionServiceDTO(InstitutionService institutionService);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("idServices", "services.id");
        return clavesToSort;
    }

}

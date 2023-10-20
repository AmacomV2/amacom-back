package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.model.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstitutionMapper {

    InstitutionMapper INSTANCE = Mappers.getMapper(InstitutionMapper.class);

    Institution toInstitution(InstitutionDTO institutionDTO);

    @Mapping(target = "idTipoInstitucion", source = "tipoInstitucion.id")
    InstitutionDTO toInstitutionDTO(Institution institution);
}

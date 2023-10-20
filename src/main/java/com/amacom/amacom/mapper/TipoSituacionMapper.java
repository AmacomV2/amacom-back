package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.TipoSituacionDTO;
import com.amacom.amacom.model.TipoSituacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoSituacionMapper {

    TipoSituacionMapper INSTANCE = Mappers.getMapper(TipoSituacionMapper.class);

    TipoSituacion toTipoSituacion(TipoSituacionDTO tipoSituacionDTO);

    TipoSituacionDTO toTipoSituacionDTO(TipoSituacion tipoSituacion);
}

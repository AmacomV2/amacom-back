package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.TipoInstitucionDTO;
import com.amacom.amacom.model.TipoInstitucion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoInstitucionMapper {

    TipoInstitucionMapper INSTANCE = Mappers.getMapper(TipoInstitucionMapper.class);

    TipoInstitucion toTipoInstitucion(TipoInstitucionDTO tipoInstitucionDTO);

    TipoInstitucionDTO toTipoInstitucionDTO(TipoInstitucion tipoInstitucion);
}

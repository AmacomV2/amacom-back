package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.TipoDocumentoDTO;
import com.amacom.amacom.model.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoDocumentoMapper {

    TipoDocumentoMapper INSTANCE = Mappers.getMapper(TipoDocumentoMapper.class);

    //DTO TO ENTITY

    TipoDocumento toTipoDocumento(TipoDocumentoDTO tipoDocumentoDTO);

    //ENTITY TO DTO

    TipoDocumentoDTO toTipoDocumentoDTO(TipoDocumento tipoDocumento);
}

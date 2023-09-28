package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.TipoEventoDTO;
import com.amacom.amacom.model.TipoEvento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoEventoMapper {

    TipoEventoMapper INSTANCE = Mappers.getMapper(TipoEventoMapper.class);

    TipoEvento toTipoEvento(TipoEventoDTO tipoEventoDTO);

    TipoEventoDTO toTipoEventoDTO(TipoEvento tipoEvento);
}

package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.TipoInstitucionDTO;
import com.amacom.amacom.model.TipoInstitucion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface TipoInstitucionMapper {

    TipoInstitucionMapper INSTANCE = Mappers.getMapper(TipoInstitucionMapper.class);

    TipoInstitucion toTipoInstitucion(TipoInstitucionDTO tipoInstitucionDTO);

    TipoInstitucionDTO toTipoInstitucionDTO(TipoInstitucion tipoInstitucion);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombre", "nombre");
        clavesToSort.put("descripcion", "descripcion");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }

}

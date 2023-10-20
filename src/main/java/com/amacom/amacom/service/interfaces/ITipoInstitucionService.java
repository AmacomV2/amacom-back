package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoInstitucion;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ITipoInstitucionService {

    TipoInstitucion findById(UUID id);

    TipoInstitucion create(TipoInstitucion tipoInstitucion);

    TipoInstitucion update(TipoInstitucion tipoInstitucion);

    void deleteById(UUID id);

    TipoInstitucion getEntityFromUUID(UUID uuid);


}

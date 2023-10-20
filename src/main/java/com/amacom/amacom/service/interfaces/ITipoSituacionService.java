package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoSituacion;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ITipoSituacionService {

    TipoSituacion findById(UUID id);

    TipoSituacion create(TipoSituacion tipoSituacion);

    TipoSituacion update(TipoSituacion tipoSituacion);

    void deleteById(UUID id);

    TipoSituacion getEntityFromUUID(UUID uuid);


}

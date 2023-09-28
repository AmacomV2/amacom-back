package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoEvento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ITipoEventoService {

    List<TipoEvento> getAll();

    TipoEvento findById(UUID id);

    TipoEvento create(TipoEvento tipoEvento);

    TipoEvento update(TipoEvento tipoEvento);

    void deleteById(UUID id);


    TipoEvento getEntityFromUUID(UUID uuid);
}

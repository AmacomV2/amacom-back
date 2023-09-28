package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoDocumento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ITipoDocumentoService {

    List<TipoDocumento> getAll();

    TipoDocumento findById(UUID id);

    TipoDocumento create(TipoDocumento genero);

    TipoDocumento update(TipoDocumento genero);

    void deleteById(UUID id);

    TipoDocumento getEntityFromUUID(UUID uuid);

}

package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.TipoInstitucion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ITipoInstitucionService {

    TipoInstitucion findById(UUID id);

    Page<TipoInstitucion> findTipoInstitucion(String query, Pageable pageable);

    TipoInstitucion create(TipoInstitucion tipoInstitucion);

    TipoInstitucion update(TipoInstitucion tipoInstitucion);

    void deleteById(UUID id);

    TipoInstitucion getEntityFromUUID(UUID uuid);


}

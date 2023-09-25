package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoDocumento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITipoDocumentoService {

    List<TipoDocumento> getAll();

    TipoDocumento findById(Long id);

    TipoDocumento create(TipoDocumento genero);

    TipoDocumento update(TipoDocumento genero);

    void deleteById(Long id);
}

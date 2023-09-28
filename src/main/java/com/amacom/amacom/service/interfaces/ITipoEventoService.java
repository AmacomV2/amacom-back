package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoEvento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITipoEventoService {

    List<TipoEvento> getAll();

    TipoEvento findById(Long id);

    TipoEvento create(TipoEvento tipoEvento);

    TipoEvento update(TipoEvento tipoEvento);

    void deleteById(Long id);


}

package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoEvento;
import com.amacom.amacom.repository.ITipoEventoRepository;
import com.amacom.amacom.service.interfaces.ITipoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class TipoEventoServiceImpl implements ITipoEventoService {

    private ITipoEventoRepository tipoEventoRepository;

    private EntityManager entityManager;



    @Override
    public List<TipoEvento> getAll() {
        return this.tipoEventoRepository.findAll();
    }

    @Override
    public TipoEvento findById(Long id) {
        return this.tipoEventoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public TipoEvento create(TipoEvento tipoEvento) {
        this.validarCreacion(tipoEvento);
        tipoEvento.setFechaHoraCreacion(new Date());
        var tipoEventoBD = this.tipoEventoRepository.save(tipoEvento);
        this.entityManager.refresh(tipoEventoBD);
        return tipoEventoBD;
    }


    @Override
    public TipoEvento update(TipoEvento tipoEvento) {
        this.validarCreacion(tipoEvento);
        var tipoEventoBD = this.tipoEventoRepository.findById(tipoEvento.getId()).orElseThrow(DataNotFoundException::new);
        tipoEventoBD.setNombre(tipoEvento.getNombre());
        tipoEventoBD.setDescripcion(tipoEvento.getDescripcion());
        tipoEventoBD.setFechaHoraModificacion(new Date());
        return this.tipoEventoRepository.save(tipoEventoBD);
    }

    @Override
    public void deleteById(Long id) {
        var tipoEventoBD = this.tipoEventoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.tipoEventoRepository.deleteById(tipoEventoBD.getId());
    }

    private void validarCreacion(TipoEvento tipoEvento){

        var existsSimilar = this.tipoEventoRepository.existsByNombre(tipoEvento.getId(), tipoEvento.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }


    @Autowired
    public void setTipoEventoRepository(ITipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

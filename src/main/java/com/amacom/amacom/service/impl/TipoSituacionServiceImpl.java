package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.TipoSituacion;
import com.amacom.amacom.repository.ITipoSituacionRepository;
import com.amacom.amacom.service.interfaces.ITipoInstitucionService;
import com.amacom.amacom.service.interfaces.ITipoSituacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class TipoSituacionServiceImpl implements ITipoSituacionService {

    private ITipoSituacionRepository tipoSituacionRepository;

    private EntityManager entityManager;


    @Override
    public TipoSituacion getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return tipoSituacionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public TipoSituacion findById(UUID id) {
        return this.tipoSituacionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public TipoSituacion create(TipoSituacion tipoSituacion) {
        this.validarCreacion(tipoSituacion);
        tipoSituacion.setId(UUID.randomUUID());
        tipoSituacion.setFechaHoraCreacion(new Date());
        var tipoSituacionBD = this.tipoSituacionRepository.save(tipoSituacion);
        this.entityManager.flush();
        this.entityManager.refresh(tipoSituacionBD);
        return tipoSituacionBD;
    }

    @Override
    public TipoSituacion update(TipoSituacion tipoSituacion) {
        this.validarCreacion(tipoSituacion);
        var tipoSituacionBD = this.tipoSituacionRepository.findById(tipoSituacion.getId()).orElseThrow(DataNotFoundException::new);
        tipoSituacionBD.setNombre(tipoSituacion.getNombre());
        tipoSituacionBD.setDescripcion(tipoSituacion.getDescripcion());
        tipoSituacionBD.setFechaHoraModificacion(new Date());
        return this.tipoSituacionRepository.save(tipoSituacionBD);
    }

    @Override
    public void deleteById(UUID id) {
        var tipoSituacionBD = this.tipoSituacionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.tipoSituacionRepository.deleteById(tipoSituacionBD.getId());
    }


    private void validarCreacion(TipoSituacion tipoSituacion){

        var existsSimilar = this.tipoSituacionRepository.existsByNombre(tipoSituacion.getId(), tipoSituacion.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setTipoSituacionRepository(ITipoSituacionRepository tipoSituacionRepository) {
        this.tipoSituacionRepository = tipoSituacionRepository;
    }

}

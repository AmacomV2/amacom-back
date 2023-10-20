package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.TipoInstitucion;
import com.amacom.amacom.repository.ITipoInstitucionRepository;
import com.amacom.amacom.service.interfaces.ITipoInstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class TipoInstitucionServiceImpl implements ITipoInstitucionService {

    private ITipoInstitucionRepository tipoInstitucionRepository;

    private EntityManager entityManager;


    @Override
    public TipoInstitucion getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return tipoInstitucionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public TipoInstitucion findById(UUID id) {
        return this.tipoInstitucionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public TipoInstitucion create(TipoInstitucion tipoInstitucion) {
        this.validarCreacion(tipoInstitucion);
        tipoInstitucion.setId(UUID.randomUUID());
        tipoInstitucion.setFechaHoraCreacion(new Date());
        var tipoInstitucionBD = this.tipoInstitucionRepository.save(tipoInstitucion);
        this.entityManager.flush();
        this.entityManager.refresh(tipoInstitucionBD);
        return tipoInstitucionBD;
    }

    @Override
    public TipoInstitucion update(TipoInstitucion tipoInstitucion) {
        this.validarCreacion(tipoInstitucion);
        var tipoInstitucionBD = this.tipoInstitucionRepository.findById(tipoInstitucion.getId()).orElseThrow(DataNotFoundException::new);
        tipoInstitucionBD.setNombre(tipoInstitucion.getNombre());
        tipoInstitucionBD.setDescripcion(tipoInstitucion.getDescripcion());
        tipoInstitucionBD.setFechaHoraModificacion(new Date());
        return this.tipoInstitucionRepository.save(tipoInstitucionBD);
    }

    @Override
    public void deleteById(UUID id) {
        var tipoInstitucionBD = this.tipoInstitucionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.tipoInstitucionRepository.deleteById(tipoInstitucionBD.getId());
    }

    private void validarCreacion(TipoInstitucion tipoInstitucion){

        var existsSimilar = this.tipoInstitucionRepository.existsByNombre(tipoInstitucion.getId(), tipoInstitucion.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setTipoInstitucionRepository(ITipoInstitucionRepository tipoInstitucionRepository) {
        this.tipoInstitucionRepository = tipoInstitucionRepository;
    }

}

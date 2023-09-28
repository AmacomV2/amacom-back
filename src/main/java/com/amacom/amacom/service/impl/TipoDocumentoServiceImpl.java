package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.TipoDocumento;
import com.amacom.amacom.repository.ITipoDocumentoRepository;
import com.amacom.amacom.service.interfaces.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

    private ITipoDocumentoRepository tipoDocumentoRepository;

    private EntityManager entityManager;

    @Override
    public TipoDocumento getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return tipoDocumentoRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<TipoDocumento> getAll() {
        return this.tipoDocumentoRepository.findAll();
    }

    @Override
    public TipoDocumento findById(UUID id) {
        return this.tipoDocumentoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public TipoDocumento create(TipoDocumento tipoDocumento) {
        this.validarCreacion(tipoDocumento);
        tipoDocumento.setId(UUID.randomUUID());
        tipoDocumento.setFechaHoraCreacion(new Date());
        var tipoDocumentoBD = this.tipoDocumentoRepository.save(tipoDocumento);
        this.entityManager.flush();
        this.entityManager.refresh(tipoDocumentoBD);
        return tipoDocumentoBD;
    }


    @Override
    public TipoDocumento update(TipoDocumento tipoDocumento) {
        this.validarCreacion(tipoDocumento);
        var tipoDocumentoBD = this.tipoDocumentoRepository.findById(tipoDocumento.getId()).orElseThrow(DataNotFoundException::new);
        tipoDocumentoBD.setNombre(tipoDocumento.getNombre());
        tipoDocumentoBD.setFechaHoraModificacion(new Date());
        return this.tipoDocumentoRepository.save(tipoDocumentoBD);
    }

    @Override
    public void deleteById(UUID id) {
        var tipoDocumentoBD = this.tipoDocumentoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.tipoDocumentoRepository.deleteById(tipoDocumentoBD.getId());
    }

    private void validarCreacion(TipoDocumento tipoDocumento){

        var existsSimilar = this.tipoDocumentoRepository.existsByNombre(tipoDocumento.getId(), tipoDocumento.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setTipoDocumentoRepository(ITipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

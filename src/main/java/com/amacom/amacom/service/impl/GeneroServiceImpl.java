package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoDocumento;
import com.amacom.amacom.repository.IGeneroRepository;
import com.amacom.amacom.service.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GeneroServiceImpl implements IGeneroService {

    private IGeneroRepository generoRepository;

    private EntityManager entityManager;

    @Override
    public Genero getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return generoRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<Genero> getAll() {
        return this.generoRepository.findAll();
    }

    @Override
    public Genero findById(UUID id) {
        return this.generoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Genero create(Genero genero) {
        this.validarCreacion(genero);
        genero.setId(UUID.randomUUID());
        genero.setFechaHoraCreacion(new Date());
        var generoBD = this.generoRepository.save(genero);
        this.entityManager.flush();
        this.entityManager.refresh(generoBD);
        return generoBD;
    }


    @Override
    public Genero update(Genero genero) {
        this.validarCreacion(genero);
        var generoBD = this.generoRepository.findById(genero.getId()).orElseThrow(DataNotFoundException::new);
        generoBD.setNombre(genero.getNombre());
        generoBD.setFechaHoraModificacion(new Date());
        return this.generoRepository.save(generoBD);
    }

    @Override
    public void deleteById(UUID id) {
        var generoBD = this.generoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.generoRepository.deleteById(generoBD.getId());
    }

    private void validarCreacion(Genero genero){

        var existsSimilar = this.generoRepository.existsByNombre(genero.getId(), genero.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setGeneroRepository(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

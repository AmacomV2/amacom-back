package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.*;
import com.amacom.amacom.repository.IPersonBabysRepository;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.service.interfaces.IPersonBabysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class PersonBabysServiceImpl implements IPersonBabysService {

    private IPersonBabysRepository personBabysRepository;

    private IPersonaRepository personaRepository;

    private EntityManager entityManager;

    @Override
    public PersonBabys getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personBabysRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public Page<PersonBabys> findPersonBabys(UUID idPersona, String query, Pageable pageable){

        Page<PersonBabys> personBabysPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("bebe.nombreAndApellido").ascending().and(Sort.by("fechaHoraCreacion").descending()));
            personBabysPage = this.personBabysRepository.findPersonBabys(idPersona, query, pageableDefault);
        }
        else{
            personBabysPage = this.personBabysRepository.findPersonBabys(idPersona, query, pageable);
        }
        return personBabysPage;
    }


    @Override
    public PersonBabys findById(UUID id) {
        return this.personBabysRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonBabys create(PersonBabys personBabys) {
        this.validarCreacion(personBabys);
        personBabys.setId(UUID.randomUUID());
        personBabys.setFechaHoraCreacion(new Date());
        var personBabysBD = this.personBabysRepository.save(personBabys);
        this.entityManager.flush();
        this.entityManager.refresh(personBabysBD);
        return personBabysBD;
    }

    @Override
    public PersonBabys update(PersonBabys personBabys) {
        this.validarCreacion(personBabys);
        var personBabysBD = this.personBabysRepository.findById(personBabys.getId()).orElseThrow(DataNotFoundException::new);
        personBabysBD.setPadre(personBabys.getPadre());
        personBabysBD.setBebe(personBabys.getBebe());
        personBabysBD.setFechaHoraModificacion(new Date());
        return this.personBabysRepository.save(personBabysBD);
    }

    private void validarCreacion(PersonBabys personBabys){
        if (personBabys.getPadre().equals(personBabys.getBebe()))
            throw new ValidacionException("El identificador no puede ser el mismo.");
    }

    @Override
    public void deleteById(UUID id) {
        var generoBD = this.personBabysRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personBabysRepository.deleteById(generoBD.getId());
    }

    @Autowired
    public void setPersonBabysRepository(IPersonBabysRepository personBabysRepository) {
        this.personBabysRepository = personBabysRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
}

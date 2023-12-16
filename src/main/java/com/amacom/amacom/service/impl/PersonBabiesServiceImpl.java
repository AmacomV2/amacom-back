package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.PersonBabies;
import com.amacom.amacom.repository.IPersonBabiesRepository;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.service.interfaces.IPersonBabiesService;

@Service
public class PersonBabiesServiceImpl implements IPersonBabiesService {

    private IPersonBabiesRepository personBabiesRepository;

    private IPersonRepository personRepository;

    private EntityManager entityManager;

    @Override
    public PersonBabies getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personBabiesRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<PersonBabies> findPersonBabies(UUID personId, String query, Pageable pageable) {

        Page<PersonBabies> personBabiesPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("child.name").ascending().and(Sort.by("createdAt").descending()));
            personBabiesPage = this.personBabiesRepository.findPersonBabies(personId, query, pageableDefault);
        } else {
            personBabiesPage = this.personBabiesRepository.findPersonBabies(personId, query, pageable);
        }
        return personBabiesPage;
    }

    @Override
    public PersonBabies findById(UUID id) {
        return this.personBabiesRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonBabies create(PersonBabies personBabies) {
        this.validateCreation(personBabies);
        personBabies.setId(UUID.randomUUID());
        var personBabiesBD = this.personBabiesRepository.save(personBabies);
        this.entityManager.flush();
        this.entityManager.refresh(personBabiesBD);
        return personBabiesBD;
    }

    @Override
    public PersonBabies update(PersonBabies personBabies) {
        this.validateCreation(personBabies);
        var personBabiesBD = this.personBabiesRepository.findById(personBabies.getId())
                .orElseThrow(DataNotFoundException::new);
        personBabiesBD.setParent(personBabies.getParent());
        personBabiesBD.setChild(personBabies.getChild());
        return this.personBabiesRepository.save(personBabiesBD);
    }

    private void validateCreation(PersonBabies personBabies) {
        if (personBabies.getParent().equals(personBabies.getChild()))
            throw new ValidationException("El identificador no puede ser el mismo.");
    }

    @Override
    public void deleteById(UUID id) {
        var genderBD = this.personBabiesRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personBabiesRepository.deleteById(genderBD.getId());
    }

    @Autowired
    public void setPersonBabiesRepository(IPersonBabiesRepository personBabiesRepository) {
        this.personBabiesRepository = personBabiesRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}

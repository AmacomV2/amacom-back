package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Person;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

    private IPersonRepository personRepository;

    private IUserRepository usersRepository;

    private EntityManager entityManager;

    @Override
    public Page<Person> findPerson(String query, Pageable pageable) {

        Page<Person> personPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            personPage = this.personRepository.findPerson(query, pageableDefault);
        } else {
            personPage = this.personRepository.findPerson(query, pageable);
        }
        return personPage;
    }

    @Override
    public Person getPersonFromUUID(UUID personId) {
        if (personId != null) {
            return personRepository.findById(personId).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Person findPersonByUser(String username) {
        this.setUserRepository(usersRepository);
        var userData = this.usersRepository.findByUsername(username);
        return this.findPersonById(userData.get().getId());
    }

    @Override
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person findPersonById(UUID id) {
        return this.personRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Person createPerson(Person person) {
        this.validatePersonCreation(person);
        person.setId(UUID.randomUUID());
        Person personDB = this.personRepository.save(person);
        this.entityManager.flush();
        this.entityManager.refresh(personDB);
        return personDB;
    }

    @Override
    public Person updatePerson(Person person) {
        this.validatePersonCreation(person);
        var personDB = this.personRepository.findById(person.getId()).orElseThrow(DataNotFoundException::new);
        personDB.setDocumentType(personDB.getDocumentType());
        personDB.setDocumentNo(person.getDocumentNo());
        personDB.setName(person.getName());
        personDB.setLastName(person.getLastName());
        personDB.setGender(person.getGender());
        personDB.setAddress(person.getAddress());
        personDB.setCivilStatus(person.getCivilStatus());
        personDB.setOccupation(person.getOccupation());
        personDB.setBirthDate(person.getBirthDate());
        personDB.setConsentText(person.getConsentText());
        personDB.setPrivacyPolicy(person.getPrivacyPolicy());
        personDB.setEvaluationCompleted(person.getEvaluationCompleted());
        personDB.setImageUrl(person.getImageUrl());
        return this.personRepository.save(personDB);
    }

    @Override
    public void deletePersonById(UUID id) {
        var personDB = this.personRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personRepository.deleteById(personDB.getId());
    }

    public void validatePersonCreation(Person person) {

        var existsSimilar = this.personRepository.existsByDocument(person.getId(), person.getDocumentNo());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Document No. already in use.");
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void setUserRepository(IUserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}

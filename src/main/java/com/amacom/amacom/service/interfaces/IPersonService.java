package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Person;

@Service
public interface IPersonService {

    Page<Person> findPerson(String query, Pageable pageable);

    Person getPersonFromUUID(UUID personId);

    List<Person> getAll();

    Person findPersonById(UUID id);

    Person findPersonByUser(String username);

    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePersonById(UUID id);

}

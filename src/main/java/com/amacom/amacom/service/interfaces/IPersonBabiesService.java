package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.PersonBabies;

@Service
public interface IPersonBabiesService {

    PersonBabies findById(UUID id);

    Page<PersonBabies> findPersonBabies(UUID personId, String query, Pageable pageable);

    PersonBabies create(PersonBabies personBabies);

    PersonBabies update(PersonBabies personBabies);

    void deleteById(UUID id);

    PersonBabies getEntityFromUUID(UUID uuid);

}

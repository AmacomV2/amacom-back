package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPersonBabysService {

    PersonBabys findById(UUID id);

    Page<PersonBabys> findPersonBabys(UUID idPersona, String query, Pageable pageable);

    PersonBabys create(PersonBabys personBabys);

    PersonBabys update(PersonBabys personBabys);

    void deleteById(UUID id);

    PersonBabys getEntityFromUUID(UUID uuid);

}

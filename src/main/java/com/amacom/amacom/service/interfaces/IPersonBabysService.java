package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPersonBabysService {

    PersonBabys findById(UUID id);

    PersonBabys create(PersonBabys personBabys);

    PersonBabys update(PersonBabys personBabys);

    void deleteById(UUID id);

    PersonBabys getEntityFromUUID(UUID uuid);

}

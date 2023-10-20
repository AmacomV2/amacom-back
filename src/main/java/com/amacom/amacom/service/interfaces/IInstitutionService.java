package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Institution;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInstitutionService {

    Institution findById(UUID id);

    Institution create(Institution institution);

    Institution update(Institution institution);

    void deleteById(UUID id);

    Institution getEntityFromUUID(UUID uuid);


}

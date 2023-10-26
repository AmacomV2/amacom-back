package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInstitutionService {

    Institution findById(UUID id);

    Page<Institution> findInstitution(UUID idTipoInstitucion, String query, Pageable pageable);

    Institution create(Institution institution);

    Institution update(Institution institution);

    void deleteById(UUID id);

    Institution getEntityFromUUID(UUID uuid);


}

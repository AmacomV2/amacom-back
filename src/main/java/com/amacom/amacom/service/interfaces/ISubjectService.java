package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Subject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISubjectService {

    Subject findById(UUID id);

    Subject create(Subject subject);

    Subject update(Subject subject);

    void deleteById(UUID id);

    Subject getEntityFromUUID(UUID uuid);


}

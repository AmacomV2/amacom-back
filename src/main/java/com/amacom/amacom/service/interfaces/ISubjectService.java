package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ISubjectService {

    Subject findById(UUID id);

    Page<Subject> findSubjectList(List<UUID> idSubjectList, String query, Pageable pageable);

    Page<Subject> findSubject(UUID idSubjectParent, String nombre, String query, Pageable pageable);

    Subject create(Subject subject);

    Subject update(Subject subject);

    void deleteById(UUID id);

    Subject getEntityFromUUID(UUID uuid);


}

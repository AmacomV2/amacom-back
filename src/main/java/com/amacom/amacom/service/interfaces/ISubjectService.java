package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Subject;

@Service
public interface ISubjectService {

    Subject findById(UUID id);

    Page<Subject> findSubjectList(List<UUID> subjectIdList, String query, Pageable pageable);

    Page<Subject> findSubject(UUID parentId, String name, String query, Pageable pageable);

    Subject create(Subject subject);

    Subject update(Subject subject);

    void deleteById(UUID id);

    Subject getEntityFromUUID(UUID uuid);

}

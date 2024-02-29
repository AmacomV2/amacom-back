package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.repository.ISubjectRepository;
import com.amacom.amacom.service.interfaces.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService {

    private ISubjectRepository subjectRepository;

    private EntityManager entityManager;

    @Override
    public Subject getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return subjectRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<Subject> findSubjectList(List<UUID> subjectIdList, String query, Pageable pageable) {
        Page<Subject> subjectPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            subjectPage = this.subjectRepository.findSubjectList(subjectIdList, query, pageableDefault);
        } else {
            subjectPage = this.subjectRepository.findSubjectList(subjectIdList, query, pageable);
        }
        return subjectPage;
    }

    @Override
    public Subject findById(UUID id) {
        return this.subjectRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Page<Subject> findSubject(UUID parentId, String query, Pageable pageable) {
        Page<Subject> subjectPage;

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
        }
        if (parentId == null) {
            subjectPage = this.subjectRepository.findParentSubjects(query, pageable);

        } else {

            subjectPage = this.subjectRepository.findSubject(parentId, query, pageable);
        }
        return subjectPage;
    }

    @Transactional
    @Override
    public Subject create(Subject subject) {
        subject.setId(UUID.randomUUID());
        var subjectBD = this.subjectRepository.save(subject);
        this.entityManager.flush();
        this.entityManager.refresh(subjectBD);
        return subjectBD;
    }

    @Override
    public Subject update(Subject subject) {
        var subjectBD = this.subjectRepository.findById(subject.getId()).orElseThrow(DataNotFoundException::new);
        subjectBD.setParent(subject.getParent());
        subjectBD.setAssociatedResult(subject.getAssociatedResult());
        subjectBD.setName(subject.getName());
        subjectBD.setValidityIndicator(subject.getValidityIndicator());
        return this.subjectRepository.save(subjectBD);
    }

    @Override
    public void deleteById(UUID id) {
        var subjectBD = this.subjectRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.subjectRepository.deleteById(subjectBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSubjectRepository(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

}

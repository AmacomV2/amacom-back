package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.repository.ISubjectRepository;
import com.amacom.amacom.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
    public Subject findById(UUID id) {
        return this.subjectRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Subject create(Subject subject) {
        subject.setId(UUID.randomUUID());
        subject.setFechaHoraCreacion(new Date());
        var subjectBD = this.subjectRepository.save(subject);
        this.entityManager.flush();
        this.entityManager.refresh(subjectBD);
        return subjectBD;
    }

    @Override
    public Subject update(Subject subject) {
        var subjectBD = this.subjectRepository.findById(subject.getId()).orElseThrow(DataNotFoundException::new);
        subjectBD.setSubjectParent(subject.getSubjectParent());
        subjectBD.setResultadosAsociados(subject.getResultadosAsociados());
        subjectBD.setNombre(subject.getNombre());
        subjectBD.setIndicacionValidez(subject.getIndicacionValidez());
        subjectBD.setFechaHoraModificacion(new Date());
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

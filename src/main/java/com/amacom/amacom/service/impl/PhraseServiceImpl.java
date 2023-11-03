package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Phrase;
import com.amacom.amacom.repository.IPhraseRepository;
import com.amacom.amacom.service.interfaces.IPhraseService;

@Service
public class PhraseServiceImpl implements IPhraseService {

    private IPhraseRepository phraseRepository;

    private EntityManager entityManager;

    @Override
    public Phrase getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return phraseRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Phrase findById(UUID id) {
        return this.phraseRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Phrase create(Phrase phrase) {
        this.validateCreation(phrase);
        phrase.setId(UUID.randomUUID());
        phrase.setCreatedAt(new Date());
        var phraseBD = this.phraseRepository.save(phrase);
        this.entityManager.flush();
        this.entityManager.refresh(phraseBD);
        return phraseBD;
    }

    @Override
    public Phrase update(Phrase phrase) {
        this.validateCreation(phrase);
        var phraseBD = this.phraseRepository.findById(phrase.getId()).orElseThrow(DataNotFoundException::new);
        phraseBD.setName(phrase.getName());
        phraseBD.setValidezIndicacion(phrase.getValidezIndicacion());
        phraseBD.setUpdatedAt(new Date());
        return this.phraseRepository.save(phraseBD);
    }

    @Override
    public void deleteById(UUID id) {
        var phraseBD = this.phraseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.phraseRepository.deleteById(phraseBD.getId());
    }

    private void validateCreation(Phrase phrase) {

        var existsSimilar = this.phraseRepository.existByName(phrase.getId(), phrase.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPhraseRepository(IPhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

}

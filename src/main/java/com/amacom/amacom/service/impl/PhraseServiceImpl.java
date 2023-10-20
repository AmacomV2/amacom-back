package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Phrase;
import com.amacom.amacom.repository.IPhraseRepository;
import com.amacom.amacom.service.interfaces.IPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
        this.validarCreacion(phrase);
        phrase.setId(UUID.randomUUID());
        phrase.setFechaHoraCreacion(new Date());
        var phraseBD = this.phraseRepository.save(phrase);
        this.entityManager.flush();
        this.entityManager.refresh(phraseBD);
        return phraseBD;
    }

    @Override
    public Phrase update(Phrase phrase) {
        this.validarCreacion(phrase);
        var phraseBD = this.phraseRepository.findById(phrase.getId()).orElseThrow(DataNotFoundException::new);
        phraseBD.setNombre(phrase.getNombre());
        phraseBD.setValidezIndicacion(phrase.getValidezIndicacion());
        phraseBD.setFechaHoraModificacion(new Date());
        return this.phraseRepository.save(phraseBD);
    }

    @Override
    public void deleteById(UUID id) {
        var phraseBD = this.phraseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.phraseRepository.deleteById(phraseBD.getId());
    }


    private void validarCreacion(Phrase phrase){

        var existsSimilar = this.phraseRepository.existsByNombre(phrase.getId(), phrase.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
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

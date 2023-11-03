package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Phrase;

@Service
public interface IPhraseService {

    Phrase findById(UUID id);

    Phrase create(Phrase phrase);

    Phrase update(Phrase phrase);

    void deleteById(UUID id);

    Phrase getEntityFromUUID(UUID uuid);

}

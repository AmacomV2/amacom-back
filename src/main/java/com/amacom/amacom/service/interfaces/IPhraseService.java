package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Phrase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPhraseService {

    Phrase findById(UUID id);

    Phrase create(Phrase phrase);

    Phrase update(Phrase phrase);

    void deleteById(UUID id);

    Phrase getEntityFromUUID(UUID uuid);


}

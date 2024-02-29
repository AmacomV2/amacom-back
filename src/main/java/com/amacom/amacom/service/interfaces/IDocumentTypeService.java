package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.DocumentType;

@Service
public interface IDocumentTypeService {

    List<DocumentType> getAll();

    DocumentType findById(UUID id);

    DocumentType create(DocumentType gender);

    DocumentType update(DocumentType gender);

    void deleteById(UUID id);

    DocumentType getEntityFromUUID(UUID uuid);

}

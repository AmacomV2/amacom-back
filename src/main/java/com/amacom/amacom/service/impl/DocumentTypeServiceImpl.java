package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.DocumentType;
import com.amacom.amacom.repository.IDocumentTypeRepository;
import com.amacom.amacom.service.interfaces.IDocumentTypeService;

@Service
public class DocumentTypeServiceImpl implements IDocumentTypeService {

    private IDocumentTypeRepository documentTypeRepository;

    private EntityManager entityManager;

    @Override
    public DocumentType getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return documentTypeRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<DocumentType> getAll() {
        return this.documentTypeRepository.findAll();
    }

    @Override
    public DocumentType findById(UUID id) {
        return this.documentTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public DocumentType create(DocumentType documentType) {
        this.validateCreation(documentType);
        documentType.setId(UUID.randomUUID());
        var documentTypeBD = this.documentTypeRepository.save(documentType);
        this.entityManager.flush();
        this.entityManager.refresh(documentTypeBD);
        return documentTypeBD;
    }

    @Override
    public DocumentType update(DocumentType documentType) {
        this.validateCreation(documentType);
        var documentTypeBD = this.documentTypeRepository.findById(documentType.getId())
                .orElseThrow(DataNotFoundException::new);
        documentTypeBD.setName(documentType.getName());
        return this.documentTypeRepository.save(documentTypeBD);
    }

    @Override
    public void deleteById(UUID id) {
        var documentTypeBD = this.documentTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.documentTypeRepository.deleteById(documentTypeBD.getId());
    }

    private void validateCreation(DocumentType documentType) {

        var existsSimilar = this.documentTypeRepository.existByName(documentType.getId(),
                documentType.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setDocumentTypeRepository(IDocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

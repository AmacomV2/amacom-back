package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterialHasSubject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISupportMaterialHasSubjectService {

    SupportMaterialHasSubject findById(UUID id);

    SupportMaterialHasSubject create(SupportMaterialHasSubject supportMaterialHasSubject);

    SupportMaterialHasSubject update(SupportMaterialHasSubject supportMaterialHasSubject);

    void deleteById(UUID id);

    SupportMaterialHasSubject getEntityFromUUID(UUID uuid);


}

package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.SupportMaterialHasSubject;

@Service
public interface ISupportMaterialHasSubjectService {

    SupportMaterialHasSubject findById(UUID id);

    Page<SupportMaterialHasSubject> find(UUID idSupportMaterial,String query, Pageable pageable);

    SupportMaterialHasSubject create(SupportMaterialHasSubject supportMaterialHasSubject);

    SupportMaterialHasSubject update(SupportMaterialHasSubject supportMaterialHasSubject);

    void deleteById(UUID id);

    SupportMaterialHasSubject getEntityFromUUID(UUID uuid);

}

package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.SupportMaterial;

@Service
public interface ISupportMaterialService {

    SupportMaterial findById(UUID id);

    Page<SupportMaterial> findSupportMaterial(UUID subjectId, String query, Pageable pageable);

    SupportMaterial create(SupportMaterial supportMaterial);

    SupportMaterial update(SupportMaterial supportMaterial);

    void deleteById(UUID id);

    SupportMaterial getEntityFromUUID(UUID uuid);

}

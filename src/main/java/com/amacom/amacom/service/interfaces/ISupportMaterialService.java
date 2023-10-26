package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISupportMaterialService {

    SupportMaterial findById(UUID id);

    Page<SupportMaterial> findSupportMaterial(UUID idSubject, String query, Pageable pageable);

    SupportMaterial create(SupportMaterial supportMaterial);

    SupportMaterial update(SupportMaterial supportMaterial);

    void deleteById(UUID id);

    SupportMaterial getEntityFromUUID(UUID uuid);


}

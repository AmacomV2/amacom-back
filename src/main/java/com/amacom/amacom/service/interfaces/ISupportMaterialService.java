package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterial;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISupportMaterialService {

    SupportMaterial findById(UUID id);

    SupportMaterial create(SupportMaterial supportMaterial);

    SupportMaterial update(SupportMaterial supportMaterial);

    void deleteById(UUID id);

    SupportMaterial getEntityFromUUID(UUID uuid);


}

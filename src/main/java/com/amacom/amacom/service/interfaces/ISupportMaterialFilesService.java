package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISupportMaterialFilesService {

    SupportMaterialFiles findById(UUID id);

    SupportMaterialFiles create(SupportMaterialFiles supportMaterialFiles);

    SupportMaterialFiles update(SupportMaterialFiles supportMaterialFiles);

    void deleteById(UUID id);

    SupportMaterialFiles getEntityFromUUID(UUID uuid);


}

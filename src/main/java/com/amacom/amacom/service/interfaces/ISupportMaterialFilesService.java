package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISupportMaterialFilesService {

    SupportMaterialFiles findById(UUID id);

    Page<SupportMaterialFiles> findSupportMaterialFiles(UUID idSupportMaterial, String query, Pageable pageable);

    SupportMaterialFiles create(SupportMaterialFiles supportMaterialFiles);

    SupportMaterialFiles update(SupportMaterialFiles supportMaterialFiles);

    void deleteById(UUID id);

    SupportMaterialFiles getEntityFromUUID(UUID uuid);


}

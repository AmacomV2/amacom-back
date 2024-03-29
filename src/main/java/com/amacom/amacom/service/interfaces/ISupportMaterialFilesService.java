package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.SupportMaterialFiles;

@Service
public interface ISupportMaterialFilesService {

    SupportMaterialFiles findById(UUID id);

    Page<SupportMaterialFiles> findSupportMaterialFiles(UUID idSupportMaterial, String query, Pageable pageable);

    SupportMaterialFiles create(SupportMaterialFiles supportMaterialFiles);

    SupportMaterialFiles update(SupportMaterialFiles supportMaterialFiles);

    void deleteById(UUID id);

    SupportMaterialFiles getEntityFromUUID(UUID uuid);

}

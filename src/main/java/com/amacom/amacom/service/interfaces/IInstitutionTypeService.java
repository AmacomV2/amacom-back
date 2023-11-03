package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.InstitutionType;

@Service
public interface IInstitutionTypeService {

    InstitutionType findById(UUID id);

    Page<InstitutionType> findInstitutionType(String query, Pageable pageable);

    InstitutionType create(InstitutionType institutionType);

    InstitutionType update(InstitutionType institutionType);

    void deleteById(UUID id);

    InstitutionType getEntityFromUUID(UUID uuid);

}

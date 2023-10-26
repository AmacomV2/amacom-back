package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IInstitutionRepository;
import com.amacom.amacom.service.interfaces.IInstitutionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class InstitutionServiceImpl implements IInstitutionService {

    private IInstitutionRepository institutionRepository;

    private EntityManager entityManager;


    @Override
    public Institution getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return institutionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<Institution> findInstitution(UUID idTipoInstitucion, String query, Pageable pageable) {
        Page<Institution> institutionPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("nombre").ascending().and(Sort.by("fechaHoraCreacion").descending()));
            institutionPage = this.institutionRepository.findInstitution(idTipoInstitucion, query, pageableDefault);
        }
        else{
            institutionPage = this.institutionRepository.findInstitution(idTipoInstitucion, query, pageable);
        }
        return institutionPage;
    }

    @Override
    public Institution findById(UUID id) {
        return this.institutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Institution create(Institution institution) {
        this.validarCreacion(institution);
        institution.setId(UUID.randomUUID());
        institution.setFechaHoraCreacion(new Date());
        var institutionBD = this.institutionRepository.save(institution);
        this.entityManager.flush();
        this.entityManager.refresh(institutionBD);
        return institutionBD;
    }

    @Override
    public Institution update(Institution institution) {
        this.validarCreacion(institution);
        var institutionBD = this.institutionRepository.findById(institution.getId()).orElseThrow(DataNotFoundException::new);
        institutionBD.setTipoInstitucion(institution.getTipoInstitucion());
        institutionBD.setNombre(institution.getNombre());
        institutionBD.setDescripcion(institution.getDescripcion());
        institutionBD.setFechaHoraModificacion(new Date());
        return this.institutionRepository.save(institutionBD);
    }

    @Override
    public void deleteById(UUID id) {
        var institutionBD = this.institutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.institutionRepository.deleteById(institutionBD.getId());
    }

    private void validarCreacion(Institution institution){

        var existsSimilar = this.institutionRepository.existsByNombre(institution.getId(), institution.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInstitutionRepository(IInstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

}

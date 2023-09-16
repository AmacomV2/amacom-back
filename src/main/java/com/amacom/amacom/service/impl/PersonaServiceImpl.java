package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    private EntityManager entityManager;

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public Persona findPersonaById(Long id) {
        return this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Persona createPersona(Persona persona) {
        this.validarCreacionPersona(persona);
        var personaBD = this.personaRepository.save(persona);
        this.entityManager.refresh(personaBD);
        return personaBD;
    }


    @Override
    public Persona updatePersona(Persona persona) {
        this.validarCreacionPersona(persona);
        var personaBD = this.personaRepository.findById(persona.getId()).orElseThrow(DataNotFoundException::new);
        personaBD.setIdTipoDocumento(personaBD.getIdTipoDocumento());
        personaBD.setDocumento(persona.getDocumento());
        personaBD.setNombre(persona.getNombre());
        personaBD.setApellido(persona.getApellido());
        personaBD.setGenero(persona.getGenero());
        personaBD.setDireccion(persona.getDireccion());
        personaBD.setEstadoCivil(persona.getEstadoCivil());
        personaBD.setOcupacion(persona.getOcupacion());
        personaBD.setFechaNacimiento(persona.getFechaNacimiento());
        personaBD.setConsentimiento(persona.getConsentimiento());
        personaBD.setPoliticaPrivacidad(persona.getPoliticaPrivacidad());
        personaBD.setEvaluacionCompletada(persona.getEvaluacionCompletada());
        personaBD.setLinkFoto(persona.getLinkFoto());
        return this.personaRepository.save(personaBD);
    }

    @Override
    public void deletePersonaById(Long id) {
        var personaBD = this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personaRepository.deleteById(personaBD.getId());
    }

    private void validarCreacionPersona(Persona persona){

        var existsSimilar = this.personaRepository.existsByDocumento(persona.getId(), persona.getDocumento());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una persona con este numero de documento");
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

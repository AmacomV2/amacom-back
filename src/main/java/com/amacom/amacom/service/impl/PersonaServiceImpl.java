package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.repository.IEstadoCivilRepository;
import com.amacom.amacom.repository.IGeneroRepository;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.repository.ITipoDocumentoRepository;
import com.amacom.amacom.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    private IEstadoCivilRepository estadoCivilRepository;

    private ITipoDocumentoRepository tipoDocumentoRepository;

    private IGeneroRepository generoRepository;


    private EntityManager entityManager;



    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public Persona findPersonaById(UUID id) {
        return this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Persona createPersona(Persona persona) {
        this.validarCreacionPersona(persona);
        persona.setId(UUID.randomUUID());
        persona.setFechaHoraCreacion(new Date());
        var personaBD = this.personaRepository.save(persona);
        this.entityManager.flush();
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
        personaBD.setIdEstadoCivil(persona.getIdEstadoCivil());
        personaBD.setOcupacion(persona.getOcupacion());
        personaBD.setFechaNacimiento(persona.getFechaNacimiento());
        personaBD.setConsentimiento(persona.getConsentimiento());
        personaBD.setPoliticaPrivacidad(persona.getPoliticaPrivacidad());
        personaBD.setEvaluacionCompletada(persona.getEvaluacionCompletada());
        personaBD.setLinkFoto(persona.getLinkFoto());
        personaBD.setFechaHoraModificacion(new Date());
        return this.setValoresDTO(personaBD);
    }

    @Override
    public void deletePersonaById(UUID id) {
        var personaBD = this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personaRepository.deleteById(personaBD.getId());
    }

    private void validarCreacionPersona(Persona persona){

        var existsSimilar = this.personaRepository.existsByDocumento(persona.getId(), persona.getDocumento());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una persona con este numero de documento");
    }

    private Persona setValoresDTO(Persona persona){

        var personaSaved = this.personaRepository.save(persona);
        var tipoDocumento = this.tipoDocumentoRepository.findById(personaSaved.getIdTipoDocumento()).orElseThrow(DataNotFoundException::new);
        var estadoCivil = this.estadoCivilRepository.findById(personaSaved.getIdEstadoCivil()).orElseThrow(DataNotFoundException::new);
        var genero = this.generoRepository.findById(personaSaved.getIdGenero()).orElseThrow(DataNotFoundException::new);
        if(tipoDocumento!= null){
            personaSaved.setTipoDocumento(tipoDocumento);
        }
        if(estadoCivil!= null){
            personaSaved.setEstadoCivil(estadoCivil);
        }
        if(genero!= null){
            personaSaved.setGenero(genero);
        }
        return personaSaved;
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setEstadoCivilRepository(IEstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    @Autowired
    public void setTipoDocumentoRepository(ITipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Autowired
    public void setGeneroRepository(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

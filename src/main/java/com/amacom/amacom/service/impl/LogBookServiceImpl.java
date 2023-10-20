package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.repository.ILogBookRepository;
import com.amacom.amacom.service.interfaces.ILogBookService;
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
public class LogBookServiceImpl implements ILogBookService {

    private ILogBookRepository logBookRepository;

    private EntityManager entityManager;


    @Override
    public Page<LogBook> findLogBook(UUID idPersona, String query, Pageable pageable){

        Page<LogBook> logBookPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("nombre").ascending().and(Sort.by("fechaHoraCreacion").descending()));
            logBookPage = this.logBookRepository.findLogBook(idPersona, query, pageableDefault);
        }
        else{
            logBookPage = this.logBookRepository.findLogBook(idPersona, query, pageable);
        }
        return logBookPage;
    }

    @Override
    public LogBook getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return logBookRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public LogBook findById(UUID id) {
        return this.logBookRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public LogBook create(LogBook logBook) {
        logBook.setId(UUID.randomUUID());
        logBook.setFechaHoraCreacion(new Date());
        var logBookBD = this.logBookRepository.save(logBook);
        this.entityManager.flush();
        this.entityManager.refresh(logBookBD);
        return logBookBD;
    }

    @Override
    public LogBook update(LogBook logBook) {
        var logBookBD = this.logBookRepository.findById(logBook.getId()).orElseThrow(DataNotFoundException::new);
        logBookBD.setPersona(logBook.getPersona());
        logBookBD.setNombre(logBook.getNombre());
        logBookBD.setDescripcion(logBook.getDescripcion());
        logBookBD.setFechaHoraModificacion(new Date());
        return this.logBookRepository.save(logBookBD);
    }

    @Override
    public void deleteById(UUID id) {
        var logBookBD = this.logBookRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.logBookRepository.deleteById(logBookBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setLogBookRepository(ILogBookRepository logBookRepository) {
        this.logBookRepository = logBookRepository;
    }

}

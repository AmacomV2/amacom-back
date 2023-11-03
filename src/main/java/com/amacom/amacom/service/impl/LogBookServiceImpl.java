package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.repository.ILogBookRepository;
import com.amacom.amacom.service.interfaces.ILogBookService;

@Service
public class LogBookServiceImpl implements ILogBookService {

    private ILogBookRepository logBookRepository;

    private EntityManager entityManager;

    @Override
    public Page<LogBook> findLogBook(UUID personId, String query, Pageable pageable) {

        Page<LogBook> logBookPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            logBookPage = this.logBookRepository.findLogBook(personId, query, pageableDefault);
        } else {
            logBookPage = this.logBookRepository.findLogBook(personId, query, pageable);
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
        logBook.setCreatedAt(new Date());
        var logBookBD = this.logBookRepository.save(logBook);
        this.entityManager.flush();
        this.entityManager.refresh(logBookBD);
        return logBookBD;
    }

    @Override
    public LogBook update(LogBook logBook) {
        var logBookBD = this.logBookRepository.findById(logBook.getId()).orElseThrow(DataNotFoundException::new);
        logBookBD.setPerson(logBook.getPerson());
        logBookBD.setName(logBook.getName());
        logBookBD.setDescription(logBook.getDescription());
        logBookBD.setUpdatedAt(new Date());
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

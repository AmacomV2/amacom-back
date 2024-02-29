package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.LogBook;

@Service
public interface ILogBookService {

    Page<LogBook> findLogBook(UUID personId, String query, Pageable pageable);

    LogBook findById(UUID id);

    LogBook create(LogBook logBook);

    LogBook update(LogBook logBook);

    void deleteById(UUID id);

    LogBook getEntityFromUUID(UUID uuid);

}

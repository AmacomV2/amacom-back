package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ILogBookService {

    Page<LogBook> findLogBook(UUID idPersona, String query, Pageable pageable);

    LogBook findById(UUID id);

    LogBook create(LogBook logBook);

    LogBook update(LogBook logBook);

    void deleteById(UUID id);

    LogBook getEntityFromUUID(UUID uuid);


}

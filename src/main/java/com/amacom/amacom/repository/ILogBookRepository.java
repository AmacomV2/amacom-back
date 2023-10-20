package com.amacom.amacom.repository;

import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ILogBookRepository extends JpaRepository<LogBook, UUID> {

    @Query("SELECT t " +
            "FROM LogBook t " +
            "WHERE (t.persona.id = :idPersona OR :idPersona IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<LogBook> findLogBook(UUID idPersona, String query, Pageable pageable);

}

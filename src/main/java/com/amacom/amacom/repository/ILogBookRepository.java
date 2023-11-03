package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.LogBook;

@Repository
public interface ILogBookRepository extends JpaRepository<LogBook, UUID> {

    @Query("SELECT t " +
            "FROM LogBook t " +
            "WHERE (t.person.id = :personId OR :personId IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
            +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<LogBook> findLogBook(UUID personId, String query, Pageable pageable);

}

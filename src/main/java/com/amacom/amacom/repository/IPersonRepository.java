package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Person p " +
                        "WHERE (p.id <> :id or :id is null) " +
                        "AND p.documentNo = :documentNo ")
        Boolean existsByDocument(UUID id, String documentNo);

        @Query("SELECT t " +
                        "FROM Person t " +
                        "WHERE CONCAT(UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.documentNo, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Person> findPerson(String query, Pageable pageable);

}

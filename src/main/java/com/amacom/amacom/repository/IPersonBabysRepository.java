package com.amacom.amacom.repository;

import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPersonBabysRepository extends JpaRepository<PersonBabys, UUID> {

    @Query("SELECT t " +
            "FROM PersonBabys t " +
            "WHERE (t.padre.id = :idPersona OR :idPersona IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.padre.nombreAndApellido , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.bebe.nombreAndApellido, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<PersonBabys> findPersonBabys(UUID idPersona, String query, Pageable pageable);

}

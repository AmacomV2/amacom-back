package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.PersonBabies;

@Repository
public interface IPersonBabiesRepository extends JpaRepository<PersonBabies, UUID> {

    @Query("SELECT t " +
            "FROM PersonBabies t " +
            "WHERE (t.parent.id = :personId OR :personId IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.parent.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.child.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
            +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<PersonBabies> findPersonBabies(UUID personId, String query, Pageable pageable);

}

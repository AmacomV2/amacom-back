package com.amacom.amacom.repository;

import java.util.UUID;

import com.amacom.amacom.model.Diagnosis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Activity;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Activity p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.name = :name ")
    Boolean existByName(UUID id, String name);

    @Query("SELECT d " +
            "FROM Activity d " +
            "WHERE " +
            "CONCAT(UPPER(REPLACE(d.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(d.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Activity> search(String query, Pageable pageable);
}

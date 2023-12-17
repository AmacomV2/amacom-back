package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Services;

@Repository
public interface IServicesRepository extends JpaRepository<Services, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Services p " +
                        "WHERE (p.id <> :id or :id is null) " +
                        "AND p.name = :name ")
        Boolean existByName(UUID id, String name);

        @Query("SELECT t " +
                        "FROM Services t " +
                        "WHERE UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) " +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Services> findServices(String query, Pageable pageable);

}

package com.amacom.amacom.repository;

import com.amacom.amacom.model.SupportMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ISupportMaterialRepository extends JpaRepository<SupportMaterial, UUID> {

    @Query("SELECT sm " +
            "FROM SupportMaterial sm " +
            "INNER JOIN SupportMaterialHasSubject sms " +
            "WHERE (sms.subject.id = :idSubject OR :idSubject IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(sm.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(sm.descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<SupportMaterial> findSupportMaterial(UUID idSubject, String query, Pageable pageable);

}

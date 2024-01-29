package com.amacom.amacom.repository;


import com.amacom.amacom.model.SupportMaterialHasSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ISupportMaterialHasSubjectRepository extends JpaRepository<SupportMaterialHasSubject, UUID> {

    @Query("SELECT t FROM SupportMaterialHasSubject t " +
            "WHERE t.supportMaterial.id = :idSupportMaterial AND "+
            "CONCAT(UPPER(REPLACE(t.supportMaterial.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))," +
            "UPPER(REPLACE(t.supportMaterial.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), " +
            "UPPER(REPLACE(t.subject.name,'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')))" +
            " LIKE UPPER(CONCAT('%', :query, '%')) ")
    Page<SupportMaterialHasSubject> findPageable(UUID idSupportMaterial, String query, Pageable pageable);
}

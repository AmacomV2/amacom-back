package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.SupportMaterialFiles;

@Repository
public interface ISupportMaterialFilesRepository extends JpaRepository<SupportMaterialFiles, UUID> {

    @Query("SELECT t " +
            "FROM SupportMaterialFiles t " +
            "WHERE (t.supportMaterial.id = :idSupportMaterial OR :idSupportMaterial IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.path , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.supportMaterial.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
            +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<SupportMaterialFiles> findSupportMaterialFiles(UUID idSupportMaterial, String query, Pageable pageable);

}

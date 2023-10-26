package com.amacom.amacom.repository;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.SupportMaterialFiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISupportMaterialFilesRepository extends JpaRepository<SupportMaterialFiles, UUID> {

    @Query("SELECT t " +
            "FROM SupportMaterialFiles t " +
            "WHERE (t.supportMaterial.id = :idSupportMaterial OR :idSupportMaterial IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.path , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.supportMaterial.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<SupportMaterialFiles> findSupportMaterialFiles(UUID idSupportMaterial, String query, Pageable pageable);


}

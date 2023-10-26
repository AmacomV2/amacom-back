package com.amacom.amacom.repository;

import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ISubjectRepository extends JpaRepository<Subject, UUID> {

    @Query("SELECT t " +
            "FROM Subject t " +
            "WHERE (t.subjectParent.id = :idSubjectParent OR :idSubjectParent IS NULL) " +
            "AND (t.nombre = :nombre OR :nombre IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.indicacionValidez, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Subject> findSubject(UUID idSubjectParent, String nombre, String query, Pageable pageable);

    @Query("SELECT t " +
            "FROM Subject t " +
            "WHERE (t.id IN (:idSubjectList)) " +
            "AND (t.subjectParent.id IS NULL ) " +
            "AND CONCAT(UPPER(REPLACE(t.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.indicacionValidez, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Subject> findSubjectList(List<UUID> idSubjectList, String query, Pageable pageable);

}

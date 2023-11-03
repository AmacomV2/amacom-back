package com.amacom.amacom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, UUID> {

        @Query("SELECT t " +
                        "FROM Subject t " +
                        "WHERE (t.subjectParent.id = :parentId OR :parentId IS NULL) " +
                        "AND (t.name = :name OR :name IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.indicacionValidez, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Subject> findSubject(UUID parentId, String name, String query, Pageable pageable);

        @Query("SELECT t " +
                        "FROM Subject t " +
                        "WHERE (t.id IN (:subjectIdList)) " +
                        "AND (t.subjectParent.id IS NULL ) " +
                        "AND CONCAT(UPPER(REPLACE(t.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.indicacionValidez, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Subject> findSubjectList(List<UUID> subjectIdList, String query, Pageable pageable);

}

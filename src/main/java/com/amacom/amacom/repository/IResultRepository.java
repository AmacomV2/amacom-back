package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Result;

@Repository
public interface IResultRepository extends JpaRepository<Result, UUID> {
    @Query("SELECT r " +
            "FROM Result r " +
            "WHERE (r.diagnosis.id = :diagnosisId OR :diagnosisId IS NULL) ")
    Page<Result> findResults(UUID diagnosisId, Pageable pageable);
}

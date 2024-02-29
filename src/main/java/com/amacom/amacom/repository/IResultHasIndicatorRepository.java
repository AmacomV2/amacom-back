package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.ResultHasIndicator;

@Repository
public interface IResultHasIndicatorRepository extends JpaRepository<ResultHasIndicator, UUID> {
        @Query("SELECT r " +
                        "FROM ResultHasIndicator r " +
                        "WHERE (r.result.id = :resultId OR :resultId IS NULL)")
        Page<ResultHasIndicator> findResultIndicators(UUID resultId,
                        Pageable pageable);
}

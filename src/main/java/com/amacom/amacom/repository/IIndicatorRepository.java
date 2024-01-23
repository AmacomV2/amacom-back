package com.amacom.amacom.repository;

import com.amacom.amacom.model.Activity;
import com.amacom.amacom.model.Indicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IIndicatorRepository extends JpaRepository<Indicator, UUID> {
    @Query("SELECT d " +
            "FROM Indicator d " +
            "WHERE " +
            "d.name " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Indicator> search(String query, Pageable pageable);
}

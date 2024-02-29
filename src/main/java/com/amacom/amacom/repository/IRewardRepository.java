package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Reward;

@Repository
public interface IRewardRepository extends JpaRepository<Reward, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Reward p " +
                        "WHERE (p.id <> :id or :id is null) " +
                        "AND p.name = :name ")
        Boolean existByName(UUID id, String name);

}

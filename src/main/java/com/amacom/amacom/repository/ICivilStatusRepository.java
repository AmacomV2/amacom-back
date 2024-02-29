package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.CivilStatus;

@Repository
public interface ICivilStatusRepository extends JpaRepository<CivilStatus, UUID> {

}

package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.auth.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, UUID> {

    Rol findRolByDescription(String description);
}

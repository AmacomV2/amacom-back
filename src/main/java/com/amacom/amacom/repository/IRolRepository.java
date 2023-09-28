package com.amacom.amacom.repository;

import com.amacom.amacom.model.auth.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRolRepository extends JpaRepository<Rol, UUID> {


    Rol findRolByDescripcion(String descripcion);
}

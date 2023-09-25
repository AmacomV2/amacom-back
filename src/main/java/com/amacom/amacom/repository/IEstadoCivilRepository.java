package com.amacom.amacom.repository;

import com.amacom.amacom.model.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {
}

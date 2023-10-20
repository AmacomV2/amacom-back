package com.amacom.amacom.repository;

import com.amacom.amacom.model.PersonSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IPersonSituationRepository extends JpaRepository<PersonSituation, UUID> {
}

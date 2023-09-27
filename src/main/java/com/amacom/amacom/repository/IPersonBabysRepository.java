package com.amacom.amacom.repository;

import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonBabysRepository extends JpaRepository<PersonBabys,Long> {
}

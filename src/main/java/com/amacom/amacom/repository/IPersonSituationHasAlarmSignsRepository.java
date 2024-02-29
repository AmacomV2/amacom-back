package com.amacom.amacom.repository;

import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.EAlarmSignType;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IPersonSituationHasAlarmSignsRepository extends JpaRepository<PersonSituationHasAlarmSigns, UUID> {

    @Query("SELECT t " +
            "FROM PersonSituationHasAlarmSigns t " +
            "WHERE (t.alarmSign.type = :type OR :type IS NULL) " +
            "AND t.personSituation.id = :situationId " +
            "AND CONCAT(UPPER(REPLACE(t.alarmSign.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.alarmSign.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<PersonSituationHasAlarmSigns> findAlarmSign(@Nullable EAlarmSignType type, String query, UUID situationId, Pageable pageable);
}

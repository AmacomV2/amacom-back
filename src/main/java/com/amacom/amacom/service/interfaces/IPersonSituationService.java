package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;
import com.amacom.amacom.model.PersonSituation;

@Service
public interface IPersonSituationService {

    PersonSituation findById(UUID id);

    PersonSituation create(PersonSituation personSituation);

    PersonSituation update(PersonSituation personSituation);

    void deleteById(UUID id);

    PersonSituation getEntityFromUUID(UUID uuid);

    Page<PersonSituation> search(@Nullable EConsultationAlert consultationAlert,
            @Nullable EConsultationStatus consultationStatus, String query, UUID personId, Pageable pageable);

}

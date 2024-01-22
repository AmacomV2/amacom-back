package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.ResultHasIndicator;

@Service
public interface IResultHasIndicatorService {

    ResultHasIndicator findById(UUID id);

    ResultHasIndicator create(ResultHasIndicator resultHasIndicator);

    ResultHasIndicator update(ResultHasIndicator resultHasIndicator);

    void deleteById(UUID id);

    ResultHasIndicator getEntityFromUUID(UUID uuid);

    Page<ResultHasIndicator> findResultIndicators(UUID resultId,
            Pageable pageable);

}

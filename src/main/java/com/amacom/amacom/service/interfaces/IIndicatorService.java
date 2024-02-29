package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Indicator;

@Service
public interface IIndicatorService {

    Indicator findById(UUID id);

    Page<Indicator> search(String query, Pageable pageable);

    Indicator create(Indicator indicator);

    Indicator update(Indicator indicator);

    void deleteById(UUID id);

    Indicator getEntityFromUUID(UUID uuid);

}

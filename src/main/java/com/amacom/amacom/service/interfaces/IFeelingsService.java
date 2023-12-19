package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Feelings;

@Service
public interface IFeelingsService {

    Feelings findById(UUID id);

    Feelings create(Feelings feelings);

    Feelings update(Feelings feelings);

    void deleteById(UUID id);

    Feelings getEntityFromUUID(UUID uuid);

    Page<Feelings> findFeeling(String query, Pageable pageable);

}

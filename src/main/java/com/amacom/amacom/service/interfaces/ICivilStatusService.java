package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.CivilStatus;

@Service
public interface ICivilStatusService {

    List<CivilStatus> getAll();

    CivilStatus getEntityFromUUID(UUID uuid);
}

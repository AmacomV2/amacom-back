package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Result;

@Service
public interface IResultService {

    Result findById(UUID id);

    Result create(Result result);

    Result update(Result result);

    void deleteById(UUID id);

    Result getEntityFromUUID(UUID uuid);

}

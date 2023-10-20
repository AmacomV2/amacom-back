package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Reward;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IRewardService {

    Reward findById(UUID id);

    Reward create(Reward reward);

    Reward update(Reward reward);

    void deleteById(UUID id);

    Reward getEntityFromUUID(UUID uuid);


}

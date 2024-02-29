package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Reward;

@Service
public interface IRewardService {

    Reward findById(UUID id);

    Reward create(Reward reward);

    Reward update(Reward reward);

    void deleteById(UUID id);

    Reward getEntityFromUUID(UUID uuid);

    List<Reward> getRankings();

}

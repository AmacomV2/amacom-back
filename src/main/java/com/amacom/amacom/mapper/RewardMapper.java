package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.RewardDTO;
import com.amacom.amacom.model.Reward;

@Mapper
public interface RewardMapper {

    RewardMapper INSTANCE = Mappers.getMapper(RewardMapper.class);

    Reward toReward(RewardDTO rewardDTO);

    RewardDTO toRewardDTO(Reward reward);
}

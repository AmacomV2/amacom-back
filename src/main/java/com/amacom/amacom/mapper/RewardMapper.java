package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.RewardDTO;
import com.amacom.amacom.model.Reward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RewardMapper {

    RewardMapper INSTANCE = Mappers.getMapper(RewardMapper.class);

    Reward toReward(RewardDTO rewardDTO);

    @Mapping(target = "idSubject", source = "subject.id")
    RewardDTO toRewardDTO(Reward reward);
}

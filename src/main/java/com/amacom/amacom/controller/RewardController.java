package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.RewardDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.RewardMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Reward;
import com.amacom.amacom.service.interfaces.IRewardService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/reward")
public class RewardController {

    private IRewardService rewardService;

    private ISubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity<RewardDTO> findById(
            @PathVariable(value = "id") UUID id){
        Reward reward =  this.rewardService.findById(id);
        if (reward == null) {
            return new ResponseEntity<>(new RewardDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(RewardMapper.INSTANCE.toRewardDTO(reward), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RewardDTO> create(
            @Valid @RequestBody RewardDTO rewardDTO){

        Reward reward = RewardMapper.INSTANCE.toReward(rewardDTO);

        reward.setSubject(this.subjectService.getEntityFromUUID(rewardDTO.getIdSubject()));

        var rewardBD = this.rewardService.create(reward);
        if(rewardBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(RewardMapper.INSTANCE.toRewardDTO(rewardBD));
    }

    @PutMapping
    public ResponseEntity<RewardDTO> update(
            @Valid @RequestBody RewardDTO rewardDTO){
        Reward reward = RewardMapper.INSTANCE.toReward(rewardDTO);

        reward.setSubject(this.subjectService.getEntityFromUUID(rewardDTO.getIdSubject()));

        var rewardBD = this.rewardService.update(reward);
        if (rewardBD == null) {
            return new ResponseEntity<>(new RewardDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(RewardMapper.INSTANCE.toRewardDTO(rewardBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.rewardService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setRewardService(IRewardService rewardService) {
        this.rewardService = rewardService;
    }


}

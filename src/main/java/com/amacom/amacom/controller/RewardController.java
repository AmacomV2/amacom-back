package com.amacom.amacom.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.RewardDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.RewardMapper;
import com.amacom.amacom.model.Reward;
import com.amacom.amacom.service.interfaces.IRewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

    private IRewardService rewardService;

    @GetMapping("/{id}")
    public ResponseEntity<RewardDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Reward reward = this.rewardService.findById(id);
        if (reward == null) {
            return new ResponseEntity<>(new RewardDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(RewardMapper.INSTANCE.toRewardDTO(reward), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> rankings() {
        try {
            List<Reward> rankings = this.rewardService.getRankings();
            return ResponseEntity
                    .ok(new SuccessDTO(rankings.stream().map(reward -> RewardMapper.INSTANCE.toRewardDTO(reward))));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody RewardDTO rewardDTO) {

        Reward reward = RewardMapper.INSTANCE.toReward(rewardDTO);

        var rewardBD = this.rewardService.create(reward);
        if (rewardBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(RewardMapper.INSTANCE.toRewardDTO(rewardBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody RewardDTO rewardDTO) {
        Reward reward = RewardMapper.INSTANCE.toReward(rewardDTO);

        var rewardBD = this.rewardService.update(reward);
        return ResponseEntity.ok(new SuccessDTO(RewardMapper.INSTANCE.toRewardDTO(rewardBD)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        try {
            this.rewardService.deleteById(id);
            return ResponseEntity.ok(new SuccessDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setRewardService(IRewardService rewardService) {
        this.rewardService = rewardService;
    }

}

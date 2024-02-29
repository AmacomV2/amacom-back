package com.amacom.amacom.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.SubjectDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.SubjectMapper;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private ISubjectService subjectService;

    @GetMapping("/getByIdList")
    public ResponseEntity<ResponseDTO> findPageableByIdList(
            Pageable pageable,
            @RequestParam(name = "subjectIdList", required = false) List<UUID> subjectIdList,
            @RequestParam(name = "query", required = false) String query) {

        var subjectPage = this.subjectService.findSubjectList(subjectIdList, query,
                ITools.getPageRequest(pageable, SubjectMapper.getSortKeys()));

        if (subjectPage == null || subjectPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(subjectPage
                .map(SubjectMapper.INSTANCE::toSubjectDTO)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "parentId", required = false) @Nullable UUID parentId,
            @RequestParam(name = "query", required = false) String query) {

        var subjectPage = this.subjectService.findSubject(parentId, query,
                ITools.getPageRequest(pageable, SubjectMapper.getSortKeys()));

        if (subjectPage == null || subjectPage.getContent().isEmpty()) {
            return new ResponseEntity<>(new ErrorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(subjectPage
                .map(SubjectMapper.INSTANCE::toSubjectDTO)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Subject subject = this.subjectService.findById(id);
        if (subject == null) {
            return new ResponseEntity<>(new SuccessDTO(new SubjectDTO()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(SubjectMapper.INSTANCE.toSubjectDTO(subject)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody SubjectDTO subjectDTO) {

        Subject subject = SubjectMapper.INSTANCE.toSubject(subjectDTO);

        if (subjectDTO.getParentId() != null) {
            subject.setParent(this.subjectService.getEntityFromUUID(subjectDTO.getParentId()));
        }

        var subjectBD = this.subjectService.create(subject);
        if (subjectBD == null)
            return new ResponseEntity<>(new ErrorDTO(), HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(SubjectMapper.INSTANCE.toSubjectDTO(subjectBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody SubjectDTO subjectDTO) {
        Subject subject = SubjectMapper.INSTANCE.toSubject(subjectDTO);

        if (subjectDTO.getParentId() != null) {
            subject.setParent(this.subjectService.getEntityFromUUID(subjectDTO.getParentId()));
        }

        var subjectBD = this.subjectService.update(subject);
        if (subjectBD == null) {
            return new ResponseEntity<>(new SuccessDTO(new SubjectDTO()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(SubjectMapper.INSTANCE.toSubjectDTO(subjectBD)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.subjectService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

}

package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.dto.RewardDTO;
import com.amacom.amacom.dto.SubjectDTO;
import com.amacom.amacom.mapper.InstitutionServiceMapper;
import com.amacom.amacom.mapper.InstitutionServicePersonMapper;
import com.amacom.amacom.mapper.RewardMapper;
import com.amacom.amacom.mapper.SubjectMapper;
import com.amacom.amacom.model.Reward;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private ISubjectService subjectService;

    @GetMapping("/getByIdList")
    public ResponseEntity<Page<SubjectDTO>> findPageableByIdList(
            Pageable pageable,
            @RequestParam(name = "idSubjectList", required = false) List<UUID> idSubjectList,
            @RequestParam(name = "query", required = false) String query) {

        var subjectPage = this.subjectService.findSubjectList(idSubjectList, query, ITools.getPageRequest(pageable, SubjectMapper.getClavesToSort()));

        if (subjectPage == null || subjectPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjectPage
                .map(SubjectMapper.INSTANCE::toSubjectDTO), HttpStatus.OK);
    }


    @GetMapping("/consulta")
    public ResponseEntity<Page<SubjectDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idSubjectParent", required = false) UUID idSubjectParent,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "query", required = false) String query) {

        var subjectPage = this.subjectService.findSubject(idSubjectParent, nombre, query, ITools.getPageRequest(pageable, SubjectMapper.getClavesToSort()));

        if (subjectPage == null || subjectPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjectPage
                .map(SubjectMapper.INSTANCE::toSubjectDTO), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> findById(
            @PathVariable(value = "id") UUID id){
        Subject subject = this.subjectService.findById(id);
        if (subject == null) {
            return new ResponseEntity<>(new SubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SubjectMapper.INSTANCE.toSubjectDTO(subject), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SubjectDTO> create(
            @Valid @RequestBody SubjectDTO subjectDTO){

        Subject subject = SubjectMapper.INSTANCE.toSubject(subjectDTO);

        if(subjectDTO.getIdSubjectParent() != null){
            subject.setSubjectParent(this.subjectService.getEntityFromUUID(subjectDTO.getIdSubjectParent()));
        }

        var subjectBD = this.subjectService.create(subject);
        if(subjectBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SubjectMapper.INSTANCE.toSubjectDTO(subjectBD));
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> update(
            @Valid @RequestBody SubjectDTO subjectDTO){
        Subject subject = SubjectMapper.INSTANCE.toSubject(subjectDTO);

        if(subjectDTO.getIdSubjectParent() != null){
            subject.setSubjectParent(this.subjectService.getEntityFromUUID(subjectDTO.getIdSubjectParent()));
        }

        var subjectBD = this.subjectService.update(subject);
        if (subjectBD == null) {
            return new ResponseEntity<>(new SubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SubjectMapper.INSTANCE.toSubjectDTO(subjectBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.subjectService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }


}

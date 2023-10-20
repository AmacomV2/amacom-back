package com.amacom.amacom.controller;

import com.amacom.amacom.dto.LogBookDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.mapper.LogBookMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.PersonaMapper;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.ILogBookService;
import com.amacom.amacom.service.interfaces.IPersonaService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/logBook")
public class LogBookController {

    private ILogBookService logBookService;

    private IPersonaService personaService;


    @GetMapping("/consulta")
    public ResponseEntity<Page<LogBookDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idPersona", required = false) UUID idPersona,
            @RequestParam(name = "query", required = false) String query) {

        var logBookPage = this.logBookService.findLogBook(idPersona, query, ITools.getPageRequest(pageable, LogBookMapper.getClavesToSort()));

        if (logBookPage == null || logBookPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logBookPage
                .map(LogBookMapper.INSTANCE::toLogBookDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogBookDTO> findById(
            @PathVariable(value = "id") UUID id){
        LogBook logBook = this.logBookService.findById(id);
        if (logBook == null) {
            return new ResponseEntity<>(new LogBookDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(LogBookMapper.INSTANCE.toLogBookDTO(logBook), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LogBookDTO> create(
            @Valid @RequestBody LogBookDTO logBookDTO){

        LogBook logBook = LogBookMapper.INSTANCE.toLogBook(logBookDTO);

        logBook.setPersona(this.personaService.getPersonaFromUUID(logBookDTO.getIdPersona()));

        var logBookBD = this.logBookService.create(logBook);
        if(logBookBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(LogBookMapper.INSTANCE.toLogBookDTO(logBookBD));
    }

    @PutMapping
    public ResponseEntity<LogBookDTO> update(
            @Valid @RequestBody LogBookDTO logBookDTO){
        LogBook logBook = LogBookMapper.INSTANCE.toLogBook(logBookDTO);

        logBook.setPersona(this.personaService.getPersonaFromUUID(logBookDTO.getIdPersona()));

        var logBookBD = this.logBookService.update(logBook);
        if (logBookBD == null) {
            return new ResponseEntity<>(new LogBookDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(LogBookMapper.INSTANCE.toLogBookDTO(logBookBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.logBookService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setLogBookService(ILogBookService logBookService) {
        this.logBookService = logBookService;
    }

}

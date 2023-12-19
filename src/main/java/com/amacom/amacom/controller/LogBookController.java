package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.LogBookDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.LogBookMapper;
import com.amacom.amacom.model.LogBook;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.ILogBookService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/logBook")
public class LogBookController {

    private ILogBookService logBookService;

    private IPersonService personService;

    @GetMapping("/consult")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "personId", required = false) @Nullable UUID personId,
            @RequestParam(name = "query", required = false) String query) {

        try {
            if (personId == null) {
                var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = User.class.cast(authData);
                personId = user.getPerson().getId();
            }
            Page<LogBook> logBookPage = this.logBookService.findLogBook(personId, query,
                    ITools.getPageRequest(pageable, LogBookMapper.getClavesToSort()));
            return new ResponseEntity<>(new SuccessDTO(logBookPage
                    .map(LogBookMapper.INSTANCE::toLogBookDTO)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        try {
            LogBook logBook = this.logBookService.findById(id);
            if (logBook == null) {
                return new ResponseEntity<>(new ErrorDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new SuccessDTO(LogBookMapper.INSTANCE.toLogBookDTO(logBook)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody LogBookDTO logBookDTO) {
        if (logBookDTO.getPersonId() == null) {
            var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = User.class.cast(authData);
            logBookDTO.setPersonId(user.getPerson().getId());
        }
        try {
            LogBook logBook = LogBookMapper.INSTANCE.toLogBook(logBookDTO);

            logBook.setPerson(this.personService.getPersonFromUUID(logBookDTO.getPersonId()));

            var logBookBD = this.logBookService.create(logBook);
            return ResponseEntity.ok(new SuccessDTO(LogBookMapper.INSTANCE.toLogBookDTO(logBookBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody LogBookDTO logBookDTO) {
        LogBook logBook = LogBookMapper.INSTANCE.toLogBook(logBookDTO);

        logBook.setPerson(this.personService.getPersonFromUUID(logBookDTO.getPersonId()));

        var logBookBD = this.logBookService.update(logBook);
        if (logBookBD == null) {
            return new ResponseEntity<>(new ErrorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(LogBookMapper.INSTANCE.toLogBookDTO(logBookBD)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.logBookService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO(Boolean.TRUE));
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setLogBookService(ILogBookService logBookService) {
        this.logBookService = logBookService;
    }

}

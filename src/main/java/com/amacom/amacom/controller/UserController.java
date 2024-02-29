package com.amacom.amacom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.UsersDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.UsersMapper;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.service.interfaces.IUserService;
import com.amacom.amacom.util.ErrorCodes;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService usersService;

    private IPersonService personService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<UsersDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "personId", required = false) UUID personId,
            @RequestParam(name = "query", required = false) String query) {

        var usersPage = this.usersService.findUsers(personId, query,
                ITools.getPageRequest(pageable, UsersMapper.getSortKeys()));

        if (usersPage == null || usersPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usersPage
                .map(UsersMapper.INSTANCE::toUsersDTO), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<User> usersList = this.usersService.getAll();
        if (usersList == null || usersList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usersList.stream()
                .map(UsersMapper.INSTANCE::toUsersDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> findUsersById(
            @PathVariable(value = "id") UUID id) {
        User user = this.usersService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(new UsersDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsersMapper.INSTANCE.toUsersDTO(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsersDTO> updateUsers(
            @Valid @RequestBody UsersDTO usersDTO) {

        User user = UsersMapper.INSTANCE.toUserDTO(usersDTO);
        user.setPerson(this.personService.getPersonFromUUID(usersDTO.getPersonId()));
        var usersBD = this.usersService.updateUser(user);
        if (usersBD == null) {
            return new ResponseEntity<>(new UsersDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsersMapper.INSTANCE.toUsersDTO(usersBD), HttpStatus.OK);
    }

    @PostMapping(value = "/newUser")
    public ResponseEntity<ResponseDTO> newUser(@RequestBody UsersDTO userDTO) {
        if (this.usersService.findByEmail(userDTO.getEmail()) != null) {
            return new ResponseEntity<>(
                    new ErrorDTO(ErrorCodes.UNIQUE_VALUES_REQUIRED,
                            "Email already in use"),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            User user = this.usersService
                    .newUser(UsersMapper.INSTANCE.toUserDTO(userDTO));
            if (user == null) {
                return new ResponseEntity<>(
                        new ErrorDTO(ErrorCodes.ERROR_CREATING_RECORD),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new SuccessDTO(UsersMapper.INSTANCE.toUsersDTO(user)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ErrorDTO(ErrorCodes.ERROR_CREATING_RECORD),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsers(
            @PathVariable(value = "id") UUID id) {
        this.usersService.deleteUserById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setUsersService(IUserService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }
}

package com.amacom.amacom.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.amacom.amacom.dto.PersonDTO;
import com.amacom.amacom.dto.auth.ChangePasswordDTO;
import com.amacom.amacom.dto.auth.CheckCodeDTO;
import com.amacom.amacom.dto.auth.LoginRequestDTO;
import com.amacom.amacom.dto.auth.RegisterRequestDTO;
import com.amacom.amacom.dto.auth.SendCodeDTO;
import com.amacom.amacom.dto.auth.SetPasswordDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonMapper;
import com.amacom.amacom.mapper.auth.AuthResponseMapper;
import com.amacom.amacom.mapper.auth.LoginRequestMapper;
import com.amacom.amacom.mapper.auth.RegisterRequestMapper;
import com.amacom.amacom.model.Person;
import com.amacom.amacom.model.auth.AuthResponse;
import com.amacom.amacom.model.auth.ChangePasswordRequest;
import com.amacom.amacom.model.auth.LoginRequest;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.auth.IUserRepository;
import com.amacom.amacom.service.interfaces.ICivilStatusService;
import com.amacom.amacom.service.interfaces.IDocumentTypeService;
import com.amacom.amacom.service.interfaces.IGenderService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.service.interfaces.IUserService;
import com.amacom.amacom.service.interfaces.auth.IAuthService;
import com.amacom.amacom.service.interfaces.auth.IPasswordResetService;
import com.amacom.amacom.util.ErrorCodes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService IAuthService;

    private IPersonService personService;

    private IPasswordResetService passwordResetService;

    private IUserService usersService;

    private IUserRepository userRepository;

    private IDocumentTypeService documentTypeService;

    private IGenderService genderService;

    private ICivilStatusService civilStatusService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try {
            AuthResponse response = this.IAuthService
                    .login(LoginRequestMapper.INSTANCE.toLoginRequest(loginRequestDTO));
            if (response == null) {
                return new ResponseEntity<>(new ErrorDTO("Wrong credentials."), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new SuccessDTO(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        try {
            /// Validate old password
            var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = User.class.cast(authData);
            LoginRequest loginRequest = LoginRequest.buildLoginRequest(user.getUsername(),
                    changePasswordDTO.getOldPassword());
            if (this.IAuthService.validateCredentials(loginRequest)) {
                /// Change current user password
                ChangePasswordRequest request = ChangePasswordRequest.buildRequest(user.getId(),
                        changePasswordDTO.getNewPassword());

                boolean response = this.usersService
                        .changePassword(request);
                if (response) {
                    return new ResponseEntity<>(new SuccessDTO(true), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ErrorDTO(false, "Bad credentials."),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {

        if (this.usersService.validateEmail(registerRequestDTO.getEmail())) {
            return new ResponseEntity<>(
                    new ErrorDTO(ErrorCodes.UNIQUE_VALUES_REQUIRED,
                            "Email already in use"),
                    HttpStatus.BAD_REQUEST);
        }

        try {

            PersonDTO personDTO = RegisterRequestMapper.INSTANCE.toPersonDTO(registerRequestDTO);
            Person person = PersonMapper.INSTANCE.toPerson(personDTO);
            person.setGender(this.genderService.getEntityFromUUID(personDTO.getGenderId()));
            person.setCivilStatus(this.civilStatusService.getEntityFromUUID(personDTO.getCivilStatusId()));
            person.setDocumentType(this.documentTypeService.getEntityFromUUID(personDTO.getDocumentTypeId()));

            Person personBD = this.personService.createPerson(person);

            if (personBD == null) {
                return new ResponseEntity<>(new ErrorDTO(), HttpStatus.CONFLICT);
            }

            registerRequestDTO.setPersonId(personBD.getId());
            AuthResponse response = this.IAuthService
                    .register(RegisterRequestMapper.INSTANCE.toNewUserRequest(registerRequestDTO));
            if (response == null) {
                return new ResponseEntity<>(
                        new ErrorDTO(ErrorCodes.ERROR_CREATING_RECORD),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new SuccessDTO(AuthResponseMapper.INSTANCE.toAuthResponseDTO(response)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ErrorDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/sendCode")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody @Valid SendCodeDTO resetPassword) {
        User user = usersService.findByEmail(resetPassword.getEmail());
        if (user != null) {
            passwordResetService.sendPasswordResetCode(resetPassword.getEmail());
            return ResponseEntity.ok(new SuccessDTO("Code sent to email."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Email not found."));
        }
    }

    @PostMapping("/setPassword")
    public ResponseEntity<ResponseDTO> changePassword(
            @RequestBody @Valid SetPasswordDTO setPasswordDTO) {
        User user = usersService.findByEmail(setPasswordDTO.getEmail());
        var token = passwordResetService.isCodeValid(setPasswordDTO.getEmail(), setPasswordDTO.getCode());
        if (Boolean.TRUE.equals(token)) {
            if (user != null) {
                user.setPassword(passwordEncoder.encode(setPasswordDTO.getPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(new SuccessDTO("Password updated."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDTO("Email not fount."));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("Validation code is missing or not valid."));
        }
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<ResponseDTO> checkCode(
            @RequestBody @Valid CheckCodeDTO checkCodeDTO) {
        var token = passwordResetService.isCodeValid(checkCodeDTO.getEmail(), checkCodeDTO.getCode());
        if (Boolean.TRUE.equals(token)) {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessDTO());

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("Validation code is missing or not valid."));
        }
    }

    @Autowired
    public void setUsersService(IUserService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordResetService(IPasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setGenderService(IGenderService genderService) {
        this.genderService = genderService;
    }

    @Autowired
    public void setCivilStatusService(ICivilStatusService civilStatusService) {
        this.civilStatusService = civilStatusService;
    }

    @Autowired
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
}

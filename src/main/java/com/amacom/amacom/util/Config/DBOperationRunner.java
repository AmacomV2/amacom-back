package com.amacom.amacom.util.Config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.amacom.amacom.model.DocumentType;
import com.amacom.amacom.model.Gender;
import com.amacom.amacom.model.Person;
import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.repository.IDocumentTypeRepository;
import com.amacom.amacom.repository.IGenderRepository;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.repository.IRolRepository;
import com.amacom.amacom.repository.auth.IUserRepository;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IUserRepository uRepo;
    @Autowired
    IPersonRepository pRepo;
    @Autowired
    IGenderRepository gRepo;
    @Autowired
    IDocumentTypeRepository docRepo;
    @Autowired
    IRolRepository rolRepo;

    @Override
    public void run(String... args) throws Exception {

        Gender male = new Gender();
        male.setId(UUID.randomUUID());
        male.setName("Masculino");

        Gender female = new Gender();
        female.setId(UUID.randomUUID());
        female.setName("Femenino");

        DocumentType cc = new DocumentType();
        cc.setId(UUID.randomUUID());
        cc.setAcronym("C.C");
        cc.setName("Cedula de ciudadania");

        Rol adminRole = new Rol();
        adminRole.setId(UUID.randomUUID());
        adminRole.setEnumRol(ERole.ROLE_ADMIN);
        adminRole.setDescription("ADMIN");
        adminRole.setName("ADMIN");

        Rol superAdminRole = new Rol();
        superAdminRole.setId(UUID.randomUUID());
        superAdminRole.setEnumRol(ERole.ROLE_SUPER_ADMIN);
        superAdminRole.setDescription("SUPER_ADMIN");
        superAdminRole.setName("SUPER_ADMIN");

        Rol nursingRole = new Rol();
        nursingRole.setId(UUID.randomUUID());
        nursingRole.setEnumRol(ERole.ROLE_NURSING);
        nursingRole.setDescription("NURSING");
        nursingRole.setName("NURSING");

        Rol userRole = new Rol();
        userRole.setId(UUID.randomUUID());
        userRole.setEnumRol(ERole.ROLE_USER);
        userRole.setDescription("USER");
        userRole.setName("USER");

        Person adminPerson = new Person();
        adminPerson.setId(UUID.randomUUID());
        adminPerson.setName("Admin");
        adminPerson.setAddress("Example address # 01 - 23");
        adminPerson.setBirthDate(new Date());
        adminPerson.setLastName("Admin ");
        adminPerson.setConsentText(true);
        adminPerson.setPrivacyPolicy(true);
        adminPerson.setDocumentType(cc);
        adminPerson.setEvaluationCompleted(true);
        adminPerson.setDocumentNo("1029384756");
        adminPerson.setGender(male);

        User admin = new User();
        admin.setId(UUID.randomUUID());
        admin.setEmail("admin@amacom.com");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        uRepo.saveAll(Arrays.asList());

        // *** Below method List.of(....) will work for JDK 9 onwards***
        // It will not work in Java 8

        gRepo.saveAll(List.of(
                male,
                female));

        docRepo.save(cc);

        pRepo.save(adminPerson);

        rolRepo.saveAll(List.of(
                superAdminRole,
                adminRole,
                nursingRole,
                userRole));

        uRepo.save(admin);

        System.out.println("----------All Data saved into Database----------------------");
    }

}
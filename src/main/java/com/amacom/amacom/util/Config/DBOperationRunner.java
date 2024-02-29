package com.amacom.amacom.util.Config;

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
        male.setId(UUID.fromString("addea406-5585-40d4-a284-5529a07aada1"));
        male.setName("Masculino");

        Gender female = new Gender();
        female.setId(UUID.fromString("9ee184ff-36e8-4fbf-a53f-c84b31e3c592"));
        female.setName("Femenino");

        DocumentType cc = new DocumentType();
        cc.setId(UUID.fromString("49c37435-3de7-4a47-a5b7-d071cf5f0d10"));
        cc.setAcronym("C.C");
        cc.setName("Cedula de ciudadania");

        Rol adminRole = new Rol();
        adminRole.setId(UUID.fromString("911a6220-cf81-4ea5-bacd-19ad27882c11"));
        adminRole.setEnumRol(ERole.ROLE_ADMIN);
        adminRole.setDescription("ADMIN");
        adminRole.setName("ADMIN");

        Rol superAdminRole = new Rol();
        superAdminRole.setId(UUID.fromString("399e3b62-7b66-481e-aef2-a1e9d0d5806b"));
        superAdminRole.setEnumRol(ERole.ROLE_SUPER_ADMIN);
        superAdminRole.setDescription("SUPER_ADMIN");
        superAdminRole.setName("SUPER_ADMIN");

        Rol nursingRole = new Rol();
        nursingRole.setId(UUID.fromString("17743ef9-34a1-4200-a9a7-792218970a8b"));
        nursingRole.setEnumRol(ERole.ROLE_NURSING);
        nursingRole.setDescription("NURSING");
        nursingRole.setName("NURSING");

        Rol userRole = new Rol();
        userRole.setId(UUID.fromString("117345f6-6349-4dda-ba09-cf46a5d9ce17"));
        userRole.setEnumRol(ERole.ROLE_USER);
        userRole.setDescription("USER");
        userRole.setName("USER");

        Person adminPerson = new Person();
        adminPerson.setId(UUID.fromString("c532ae36-9a46-470c-ab68-db231807ab37"));
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
        admin.setId(UUID.fromString("e593431a-c7b4-41e3-bc9e-7bacfefa7ae8"));
        admin.setEmail("admin@amacom.com");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        uRepo.save(admin);

        // *** Below method List.of(....) will work for JDK 9 onwards***
        // It will not work in Java 8

        try {
            if (rolRepo.findAll().isEmpty()) {
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

        } catch (Exception e) {
            System.out.println("----------Error on saving data into DB----------------------");
            System.out.println(e.getMessage());
        }

    }

}
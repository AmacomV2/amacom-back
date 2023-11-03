package com.amacom.amacom.repository.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.auth.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
        Optional<User> findByUsername(String username);

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM User u " +
                        "WHERE (u.id <> :id or :id is null) " +
                        "AND (u.username = :username OR u.email = :email)")
        Boolean existsByUsernameOrEmail(UUID id, String username, String email);

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM User u " +
                        "WHERE (u.id <> :id or :id is null) " +
                        "AND u.person.id = :idPerson")
        Boolean existsByPersonId(UUID id, UUID idPerson);

        @Query("SELECT DISTINCT u " +
                        "FROM User u " +
                        "WHERE u.email = :email ")
        User findByEmail(String email);

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM User u " +
                        "WHERE (u.id <> :id or :id is null) " +
                        "AND u.email = :email")
        Boolean emailExists(UUID id, String email);

        @Query("SELECT t " +
                        "FROM User t " +
                        "WHERE (t.person.id = :idPerson OR :idPerson IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.username, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.person.fullName, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<User> findUser(UUID idPerson, String query, Pageable pageable);

}

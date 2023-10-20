package com.amacom.amacom.repository.auth;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.auth.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,UUID> {
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND (u.username = :username OR u.email = :email)")
    Boolean existsByUsernameOrEmail(UUID id, String username, String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.persona.id = :idPersona")
    Boolean existsByIdPersona(UUID id, UUID idPersona);


    @Query("SELECT DISTINCT u " +
            "FROM Usuario u " +
            "WHERE u.email = :email ")
    Usuario findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.email = :email")
    Boolean emailExists(UUID id, String email);

    @Query("SELECT t " +
            "FROM Usuario t " +
            "WHERE (t.persona.id = :idPersona OR :idPersona IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.username, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.persona.nombreAndApellido, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Usuario> findUsuario(UUID idPersona, String query, Pageable pageable);

}

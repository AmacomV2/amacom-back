package com.amacom.amacom.repository.auth;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amacom.amacom.model.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND (u.username = :username OR u.email = :email)")
    Boolean existsByUsernameOrEmail(Long id, String username, String email);

    @Query("SELECT CASE WHEN COUNT (u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.idPersona = :idPersona " )
    Boolean existsByIdPersona(Long id, UUID idPersona);

    @Query("SELECT DISTINCT u " +
            "FROM Usuario u " +
            "WHERE u.email = :email ")
    Usuario findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Usuario u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.email = :email")
    Boolean emailExists(Long id, String email);
}

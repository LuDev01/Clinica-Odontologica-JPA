package com.example.Clinica_Odontologica_JPA.repository;

import com.example.Clinica_Odontologica_JPA.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URLStreamHandler;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}

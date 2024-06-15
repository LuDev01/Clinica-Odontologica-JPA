package com.example.Clinica_Odontologica_JPA.repository;

import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Optional<Paciente> findByEmail(String email);
}

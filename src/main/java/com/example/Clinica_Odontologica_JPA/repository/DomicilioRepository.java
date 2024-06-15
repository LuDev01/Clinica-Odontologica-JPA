package com.example.Clinica_Odontologica_JPA.repository;

import com.example.Clinica_Odontologica_JPA.entity.Domicilio;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio,Long> {

}

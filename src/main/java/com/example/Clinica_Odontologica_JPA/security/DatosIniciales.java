package com.example.Clinica_Odontologica_JPA.security;


import com.example.Clinica_Odontologica_JPA.entity.*;
import com.example.Clinica_Odontologica_JPA.repository.UsuarioRepository;
import com.example.Clinica_Odontologica_JPA.service.OdontologoService;
import com.example.Clinica_Odontologica_JPA.service.PacienteService;
import com.example.Clinica_Odontologica_JPA.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PacienteService ps;
    @Autowired
    private OdontologoService os;
    @Autowired
    private TurnoService ts;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorgito","jpereryradh","user@correo.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        Usuario admin = new Usuario("Fabio","fabiolo","admin@correo.com",passCifrado,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(admin);


//        Paciente paciente= new Paciente("Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
//        Paciente pacienteGuardado= ps.crearPaciente(paciente);
//        Odontologo odontologo = new Odontologo(1234, "Camilo", "Torres");
//        Odontologo odontologoGuardado = os.crearOdontologo(odontologo);
//        Turno turno = new Turno(pacienteGuardado, odontologoGuardado, LocalDate.of(2024,6,20));
//        Turno turnoGuardado = ts.crearTurno(turno);

    }
}
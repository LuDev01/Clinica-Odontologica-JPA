package com.example.Clinica_Odontologica_JPA.security;


import com.example.Clinica_Odontologica_JPA.entity.Usuario;
import com.example.Clinica_Odontologica_JPA.entity.UsuarioRole;
import com.example.Clinica_Odontologica_JPA.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorgito","jpereryradh","user@correo.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        Usuario admin = new Usuario("Fabio","fabiolo","admin@correo.com",passCifrado,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(admin);
    }
}
package com.example.Clinica_Odontologica_JPA.security;

import com.example.Clinica_Odontologica_JPA.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //aca ya no funciona postman ni nada que no se autentique.
public class WebSecurityConfig {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //proveedor de autenticaciones DAO
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        //aca debe venir el usuario y desencriptador de claves
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
    //aca deberiamos hablar de las autorizaciones

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz)-> authz
                        .requestMatchers("/index.html","/get_turnos.html").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/get_pacientes.html","/post_pacientes.html", "/get_odontologos.html").hasRole("ADMIN")
                        .requestMatchers("/403.html","/403.css", "/403,js").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());
        http.exceptionHandling((exception)-> exception.accessDeniedPage("/403.html"));
        return http.build();
    }

}
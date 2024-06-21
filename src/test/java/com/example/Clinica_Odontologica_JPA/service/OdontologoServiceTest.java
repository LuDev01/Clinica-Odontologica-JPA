package com.example.Clinica_Odontologica_JPA.service;

import com.example.Clinica_Odontologica_JPA.entity.Domicilio;
import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private  OdontologoService odontologoService;

    @Test
    @Order(1)
    public void crearOdontologo(){
        Odontologo odontologo= new Odontologo(1234,"Andreany","Martinez");
        Odontologo odontologoGuardado= odontologoService.crearOdontologo(odontologo);
        assertEquals(1L,odontologoGuardado.getId());
    }


    @Test
    @Order(2)
    public void buscarPorId(){
        Long id= 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }


    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id= 1L;
        Odontologo odontologo= new Odontologo(57834,"Morelina","Suarez");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        assertEquals("Andreany", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> listaOdontologos= odontologoService.listarTodos();
        assertEquals(2,listaOdontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorId(1L);
        assertFalse(odontologoEliminado.isPresent());
    }
}

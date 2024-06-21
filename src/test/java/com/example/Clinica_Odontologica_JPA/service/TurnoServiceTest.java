package com.example.Clinica_Odontologica_JPA.service;

import com.example.Clinica_Odontologica_JPA.entity.Domicilio;
import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.entity.Turno;
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
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService os;
    @Autowired
    private TurnoService ts;


    @Test
    @Order(1)
    public void crearTurno(){
        Paciente paciente= new Paciente("Jorgito","pereyra","11111", LocalDate.of(2024,6,20),new Domicilio("calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
        Paciente pacienteGuardado= pacienteService.crearPaciente(paciente);
        Odontologo odontologo = new Odontologo(1234, "Camilo", "Torres");
        Odontologo odontologoGuardado = os.crearOdontologo(odontologo);
        Turno turno = new Turno(pacienteGuardado, odontologoGuardado, LocalDate.of(2024,6,20));
        Turno turnoGuardado = ts.crearTurno(turno);
        assertEquals(1L,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id= 1L;
        Optional<Turno> turnoBuscado = ts.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 1L;
        LocalDate nuevaFecha = LocalDate.of(2024, 12, 15);
        Optional<Turno> turnoInicial = ts.buscarPorId(id);
        if (turnoInicial.isPresent()){
            Turno turnoNuevaFecha = turnoInicial.get();
            turnoNuevaFecha.setFecha(nuevaFecha);
            ts.actualizarTurno(turnoNuevaFecha);
            Optional<Turno> turnoActualizado = ts.buscarPorId(id);
            assertEquals(nuevaFecha, turnoActualizado.get().getFecha());
        }
        assertEquals(nuevaFecha, turnoInicial.get().getFecha());
    }


    @Test
    @Order(4)
    public void ListarTodos(){
        List<Turno> listaTurnos = ts.listarTodos();
        assertEquals(1, listaTurnos.size());
    }
    @Test
    @Order(5)
    public void eliminarTurno(){
        ts.eliminarTurno(1L);
        Optional<Turno> turnoEliminado = ts.buscarPorId(1L);
        assertFalse(turnoEliminado.isPresent());
    }
}

package com.example.Clinica_Odontologica_JPA.controller;

import com.example.Clinica_Odontologica_JPA.DTO.TurnoDTO;
import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.entity.Turno;
import com.example.Clinica_Odontologica_JPA.exception.BadRequestException;
import com.example.Clinica_Odontologica_JPA.exception.ResourceNotFoundException;
import com.example.Clinica_Odontologica_JPA.service.OdontologoService;
import com.example.Clinica_Odontologica_JPA.service.PacienteService;
import com.example.Clinica_Odontologica_JPA.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")

public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>>buscarPorId(@PathVariable Long id) throws Exception {
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()){
            return ResponseEntity.ok(turno);
        }
        throw new ResourceNotFoundException("Turno no encontrado!!");
    }

    @PostMapping
    public ResponseEntity<Turno>crearTurno(@RequestBody TurnoDTO turno) throws Exception{
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPacienteId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turno.getOdontologoId());

        if (!paciente.isPresent() || !odontologo.isPresent()) {
            throw new BadRequestException("Datos inválidos");
        }

        Turno turno1 = new Turno();
        turno1.setPaciente(paciente.get());
        turno1.setOdontologo(odontologo.get());
        turno1.setFecha(turno.getFecha());

        return  ResponseEntity.ok(turnoService.crearTurno(turno1));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno>actualizarTurno(@PathVariable Long id, @RequestBody TurnoDTO turnoDTO) throws Exception {

        Optional<Turno> turno1 = turnoService.buscarPorId(id);
        Optional<Paciente> paciente =pacienteService.buscarPorId(turnoDTO.getPacienteId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoDTO.getOdontologoId());

        if (!turno1.isPresent()){
            throw new ResourceNotFoundException("Turno no encontrado!!");
        }

        if (!paciente.isPresent() || !odontologo.isPresent()){
            throw new BadRequestException("Datos inválidos");
        }


        Turno turno = turno1.get();
        turno.setPaciente(paciente.get());
        turno.setOdontologo(odontologo.get());
        turno.setFecha(turnoDTO.getFecha());
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws Exception{

            Optional<Turno> turno = turnoService.buscarPorId(id);

            if (turno.isPresent()){
                turnoService.eliminarTurno(id);
                return ResponseEntity.ok("Odontologo eliminado correctamente!");
            }
            throw new ResourceNotFoundException("Turno no encontrado");


    }
}

package com.example.Clinica_Odontologica_JPA.controller;

import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.exception.ResourceNotFoundException;
import com.example.Clinica_Odontologica_JPA.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) throws Exception{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        throw new  ResourceNotFoundException("Paciente no encontrado");

    }
    @PostMapping
    public ResponseEntity<Paciente>crearOdontologo(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.crearPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<Paciente>actualizarOdontologo(@RequestBody Paciente paciente) throws Exception{
        Optional<Paciente> paciente1 =pacienteService.buscarPorId(paciente.getId());
        if (paciente1.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(paciente);
        }
        throw new ResourceNotFoundException("Paciente no encontrado!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws Exception{
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if (paciente.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado correctamente!");
        }
        throw new ResourceNotFoundException("Paciente no encontrado");
    }

}

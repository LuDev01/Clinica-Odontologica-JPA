package com.example.Clinica_Odontologica_JPA.controller;

import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.exception.ResourceNotFoundException;
import com.example.Clinica_Odontologica_JPA.service.OdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@Tag(name = "Controller de Odontologos", description = "permite registrar, eliminar y listar")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    @Parameter(description = "nos permite listar todos los odontologos")
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>>buscarPorId(@PathVariable Long id) throws  Exception  {
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if (odontologo.isPresent()){
            return ResponseEntity.ok(odontologo);
        }
        throw new ResourceNotFoundException("Odontologo no encontrado!!");
    }

    @PostMapping
    @Operation(summary = "nos permite registrar", description = "enviar odontologo sin id")
    @ApiResponse(responseCode = "200", description = "odontologo creado con exito")
    public ResponseEntity<Odontologo>crearOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo>actualizarOdontologo(@RequestBody Odontologo odontologo) throws Exception{
        Optional<Odontologo> odontologo1 = odontologoService.buscarPorId(odontologo.getId());
        if(odontologo1.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok(odontologo);

        }
        throw new ResourceNotFoundException("Odontologo no encontrado!!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws Exception {
     Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
     if (odontologo.isPresent()){
         odontologoService.eliminarOdontologo(id);
         return ResponseEntity.ok("odontologo eliminado correctamente!");
     }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }
}

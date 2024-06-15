package com.example.Clinica_Odontologica_JPA.service;

import com.example.Clinica_Odontologica_JPA.DTO.TurnoDTO;
import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.entity.Turno;
import com.example.Clinica_Odontologica_JPA.exception.ResourceNotFoundException;
import com.example.Clinica_Odontologica_JPA.repository.OdontologoRepository;
import com.example.Clinica_Odontologica_JPA.repository.PacienteRepository;
import com.example.Clinica_Odontologica_JPA.repository.TurnoRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    public TurnoRepository turnoRepository;

    @Autowired
    public PacienteRepository pacienteRepository;

    @Autowired
    public OdontologoRepository odontologoRepository;

    public Turno crearTurno(Turno turno) {

        return turnoRepository.save(turno);
    }
    public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public List<Turno> listarTodos(){
        return turnoRepository.findAll();
    }
}

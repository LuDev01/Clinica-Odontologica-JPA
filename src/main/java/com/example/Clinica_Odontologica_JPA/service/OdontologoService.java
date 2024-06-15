package com.example.Clinica_Odontologica_JPA.service;

import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.exception.ResourceNotFoundException;
import com.example.Clinica_Odontologica_JPA.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    public OdontologoRepository odontologoRepository;

    public Odontologo crearOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) {
       Optional<Odontologo> odontologo = odontologoRepository.findById(id);
           odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }
}

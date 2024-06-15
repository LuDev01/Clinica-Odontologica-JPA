package com.example.Clinica_Odontologica_JPA.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TurnoDTO {

    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fecha;

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

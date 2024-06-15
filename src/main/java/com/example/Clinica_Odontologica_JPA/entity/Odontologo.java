package com.example.Clinica_Odontologica_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numeroMatricula;

    @Column
    private String nombre;

    @Column
    private String apellido;

}

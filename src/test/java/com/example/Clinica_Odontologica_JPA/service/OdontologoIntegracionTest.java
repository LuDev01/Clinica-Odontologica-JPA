package com.example.Clinica_Odontologica_JPA.service;


import com.example.Clinica_Odontologica_JPA.entity.Domicilio;
import com.example.Clinica_Odontologica_JPA.entity.Odontologo;
import com.example.Clinica_Odontologica_JPA.entity.Paciente;
import com.example.Clinica_Odontologica_JPA.entity.Turno;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos(){
        Odontologo odontologo= odontologoService.crearOdontologo(new Odontologo(1568,"Gina","Arias"));
    }

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Test
    public void ListarTodosLosTurnosTest() throws Exception{
       // cargarDatos();
        Odontologo odontologo = new Odontologo(5898,"Moris","Toledo");
        String jsonString = JSON_MAPPER.writeValueAsString(odontologo);
        System.out.println("Jsiton: "+ jsonString);
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.post("/odontologos").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }
}

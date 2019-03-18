package com.ffsilva.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffsilva.api.model.dto.VooRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author francisco
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class VooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String API_URI = "/voos";

    @Test
    public void testbBuscarRotasDeVoosValido() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URI)
                .content(this.getDadosDtoValidos())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isEmpty());
    }

    @Test
    public void testbBuscarRotasDeVoosInvalido() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URI)
                .content(this.getDadosDtoOrigemInvalida())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("Origem inválida."));
    }

    private String getDadosDtoValidos() throws JsonProcessingException {
        VooRequestDto dto = new VooRequestDto();

        dto.setDe("BSB");
        dto.setPara("VCP");
        dto.setDate("2019-02-10");

        return new ObjectMapper().writeValueAsString(dto);
    }

    private String getDadosDtoOrigemInvalida() throws JsonProcessingException {
        VooRequestDto dto = new VooRequestDto();

        dto.setDe("OOO");
        dto.setPara("VCP");
        dto.setDate("2019-02-10");

        return new ObjectMapper().writeValueAsString(dto);
    }
}

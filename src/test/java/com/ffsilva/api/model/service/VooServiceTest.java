package com.ffsilva.api.model.service;

import com.ffsilva.api.model.dto.RotaDto;
import com.ffsilva.api.model.dto.VooRequestDto;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author francisco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VooServiceTest {

    @Autowired
    private VooService vooService;

    @Test
    public void testBuscarRotasDeVoos() {
        List<RotaDto> rotas = this.vooService.buscarRotasDeVoos(this.getDto());

        assertTrue(!rotas.isEmpty());
    }

    private VooRequestDto getDto() {
        VooRequestDto dto = new VooRequestDto();

        dto.setDe("BSB");
        dto.setPara("VCP");
        dto.setDate("2019-02-10");

        return dto;
    }
}

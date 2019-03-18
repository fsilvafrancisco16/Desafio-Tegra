package com.ffsilva.api.controller;

import com.ffsilva.api.model.dto.RotaDto;
import com.ffsilva.api.model.dto.VooRequestDto;
import com.ffsilva.api.model.response.Response;
import com.ffsilva.api.model.service.impl.VooServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@CrossOrigin(origins = "*")
public class VooController {

    @Autowired
    private VooServiceImpl vooService;

    /**
     * Retorna as rotas de voos, dados os paramentros de pequisa passados no DTO. Os retornos são encapsulados em RespoenseEntity
     * e obedecem os retornos http.
     *
     * @param dto VooRequestDto
     * @param result BindingResult - Para verificação de erros
     * @return ResponseEntity<Response<List<RotaDto>>>
     */
    @GetMapping("/voos")
    public ResponseEntity<Response<List<RotaDto>>> buscarRotasDeVoos(@Valid @RequestBody VooRequestDto dto, BindingResult result) {
        return this.vooService.buscarRotasDeVoos(dto, result);
    }
}

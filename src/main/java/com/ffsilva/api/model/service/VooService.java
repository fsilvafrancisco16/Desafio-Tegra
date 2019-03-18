package com.ffsilva.api.model.service;

import com.ffsilva.api.model.dto.RotaDto;
import com.ffsilva.api.model.dto.VooRequestDto;
import com.ffsilva.api.model.response.Response;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 *
 * @author francisco
 */
public interface VooService {

    /**
     * Retorna as rotas de voos, dados os paramentros de pequisa passados no DTO. Os retornos são encapsulados em RespoenseEntity
     * e obedecem os retornos http.
     *
     * @param dto VooRequestDto
     * @param result BindingResult
     * @return ResponseEntity<Response<List<RotaDto>>>
     */
    ResponseEntity<Response<List<RotaDto>>> buscarRotasDeVoos(VooRequestDto dto, BindingResult result);

    /**
     * Busca todos os voos sem escala disponiveis para a rota.
     *
     * @param dto VooRequestDto
     * @return List<RotaDto>
     */
    List<RotaDto> buscarRotasSemEscala(VooRequestDto dto);
}

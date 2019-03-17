package com.ffsilva.api.model.service;

import com.ffsilva.api.model.entity.Aeroporto;
import com.ffsilva.api.model.response.Response;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author francisco
 */
public interface AeroportoService {

    /**
     * Busca e retorna todos os aeroportos, encapsulados em uma response.
     *
     * @return ResponseEntity<Response<Aeroporto>> - resposta http adequada
     */
    ResponseEntity<Response<List<Aeroporto>>> findAll();
}

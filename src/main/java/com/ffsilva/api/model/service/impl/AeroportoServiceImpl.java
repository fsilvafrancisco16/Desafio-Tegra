package com.ffsilva.api.model.service.impl;

import com.ffsilva.api.model.entity.Aeroporto;
import com.ffsilva.api.model.response.Response;
import com.ffsilva.api.model.service.AeroportoService;
import com.ffsilva.api.model.repository.impl.AeroportoRepositoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author francisco
 */
@Service
public class AeroportoServiceImpl implements AeroportoService {

    @Autowired
    private AeroportoRepositoryImpl aeroportoRepository;

    @Override
    public ResponseEntity<Response<List<Aeroporto>>> findAll() {
        Response<List<Aeroporto>> response = new Response<>();

        List<Aeroporto> aeroportos = this.aeroportoRepository.findAll();
        if (aeroportos.isEmpty()) {
            response.getErrors().add("Nenhum aeroporto disponivel.");
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(aeroportos);
        return ResponseEntity.ok(response);
    }

}

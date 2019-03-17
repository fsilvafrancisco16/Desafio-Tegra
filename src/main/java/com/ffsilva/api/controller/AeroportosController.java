package com.ffsilva.api.controller;

import com.ffsilva.api.model.entity.Aeroporto;
import com.ffsilva.api.model.response.Response;
import com.ffsilva.api.model.service.impl.AeroportoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@CrossOrigin(origins = "*")
public class AeroportosController {

    @Autowired
    private AeroportoServiceImpl aeroportoService;

    /**
     *
     * @return
     */
    @RequestMapping("/aeroporto/todos")
    public ResponseEntity<Response<List<Aeroporto>>> buscarTodosAeroportos() {
        return this.aeroportoService.findAll();
    }
}

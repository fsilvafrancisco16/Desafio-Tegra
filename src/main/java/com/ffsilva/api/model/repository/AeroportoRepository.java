package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Aeroporto;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface AeroportoRepository {

    /**
     * Ler o arquivo ./aeroportos.json com os dados dos Aeroportos e converte para lista de objetos Java.
     *
     * @return List<Aeroporto>
     */
    List<Aeroporto> findAll();
}

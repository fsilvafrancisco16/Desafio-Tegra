package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Aeroporto;
import java.util.List;
import java.util.Optional;

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

    /**
     * Busca um aeroporto na lista de dados.
     *
     * @param origem String - edenticador do aeroporto
     * @return Optional<Aeroporto>
     */
    Optional<Aeroporto> findByAeroporto(String aeroporto);
}

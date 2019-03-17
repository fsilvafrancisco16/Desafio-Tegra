package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Voo;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface VooRepository {

    /**
     * Retorna todos os voos da Operadora Uberai.
     *
     * @return List<Voo>
     */
    List<Voo> findAllVoosOperadoraUberair();

    /**
     * Retorna todos os voos da Operadora 99Planes.
     *
     * @return List<Voo>
     */
    List<Voo> findAllVoosOperadora99Planes();

    /**
     * Retorna todos os voos de todas as operadoras.
     *
     * @return List<Voo>
     */
    List<Voo> findAll();
}

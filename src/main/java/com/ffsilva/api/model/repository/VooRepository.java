package com.ffsilva.api.model.repository;

import com.ffsilva.api.model.entity.Voo;
import java.time.LocalDate;
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

    /**
     * Busca todos os voos com rotas diretas.
     *
     * @param origem String - Origem do voo
     * @param destino String - Destino do voo
     * @param data LocalDate - data do voo
     * @return List<Voo>
     */
    List<Voo> findByVoosDiretos(String origem, String destino, LocalDate data);
}

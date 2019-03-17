package com.ffsilva.api.model.dto;

import com.ffsilva.api.model.entity.Aeroporto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author francisco
 */
public class VooDto {

    private String voo;
    private Aeroporto origem;
    private Aeroporto destino;
    private LocalDateTime saida;
    private LocalDateTime chegada;
    private String operadora;
    private BigDecimal preco;

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getChegada() {
        return chegada;
    }

    public void setChegada(LocalDateTime chegada) {
        this.chegada = chegada;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "VooDto{" + "voo=" + voo + ", origem=" + origem + ", destino=" + destino + ", saida=" + saida + ", chegada=" + chegada + ", operadora=" + operadora + ", preco=" + preco + '}';
    }
}
        
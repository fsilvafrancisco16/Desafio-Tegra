package com.ffsilva.api.model.auxiliar;

import java.math.BigDecimal;

/**
 *
 * @author francisco
 */
public class VooAuxiliarConversao {

    private String voo;
    private String origem;
    private String destino;
    private String data_saida;
    private String saida;
    private String chegada;
    private BigDecimal valor;

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "VooAuxiliar{"
                + "voo=" + voo
                + ", origem=" + origem
                + ", destino=" + destino
                + ", data_saida=" + data_saida
                + ", saida=" + saida
                + ", chegada=" + chegada
                + ", valor=" + valor
                + '}';
    }
}

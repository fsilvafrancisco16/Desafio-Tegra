package com.ffsilva.api.model.dto;

import com.ffsilva.api.model.entity.Aeroporto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francisco
 */
public class RotaDto {

    private Aeroporto origem;
    private Aeroporto destino;
    private String data;
    private List<VooDto> voos = new ArrayList<>();

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<VooDto> getVoos() {
        return voos;
    }

    public void setVoos(List<VooDto> voos) {
        this.voos = voos;
    }

    @Override
    public String toString() {
        return "VooResponseDto{" + "origem=" + origem + ", destino=" + destino + ", data=" + data + ", voos=" + voos + '}';
    }
}

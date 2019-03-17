package com.ffsilva.api.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author francisco
 */
public class Aeroporto implements Serializable {

    private String aeroporto;
    private String nome;
    private String cidade;

    // -------  ------- //
    public String getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(String aeroporto) {
        this.aeroporto = aeroporto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.aeroporto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeroporto other = (Aeroporto) obj;
        return Objects.equals(this.aeroporto, other.aeroporto);
    }

    @Override
    public String toString() {
        return "Aeroporto{" + "aeroporto=" + aeroporto + ", nome=" + nome + ", cidade=" + cidade + '}';
    }
}

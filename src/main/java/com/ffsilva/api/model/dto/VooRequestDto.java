package com.ffsilva.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author francisco
 */
public class VooRequestDto {

    @NotBlank(message = "Origem não informada.")
    @Size(min = 3, max = 3, message = "A origem deve conter três caracteres.")
    private String de;

    @NotBlank(message = "Destino não informado.")
    @Size(min = 3, max = 3, message = "O destino deve conter três caracteres.")
    private String para;

    @NotBlank(message = "Data não informada.")
    private String date;

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VooRequestDto{" + "de=" + de + ", para=" + para + ", date=" + date + '}';
    }
}

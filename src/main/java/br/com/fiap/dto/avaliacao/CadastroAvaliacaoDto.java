package br.com.fiap.dto.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CadastroAvaliacaoDto {

    @Size(max = 200)
    private String descricaoNota;

    @PositiveOrZero
    @Max(10)
    private double nota;

    public String getDescricaoNota() {
        return descricaoNota;
    }

    public void setDescricaoNota(String descricaoNota) {
        this.descricaoNota = descricaoNota;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

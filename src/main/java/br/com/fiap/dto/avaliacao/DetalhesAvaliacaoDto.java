package br.com.fiap.dto.avaliacao;

import java.time.LocalDate;

public class DetalhesAvaliacaoDto {
    private int codigo;
    private String descricao;
    private LocalDate dataCadastro;
    private double nota;
    private int codigoDoce;

    public DetalhesAvaliacaoDto() {
    }

    public DetalhesAvaliacaoDto(int codigo, String descricao, LocalDate dataCadastro, double nota, int codigoDoce) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.nota = nota;
        this.codigoDoce = codigoDoce;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getCodigoDoce() {
        return codigoDoce;
    }

    public void setCodigoDoce(int codigoDoce) {
        this.codigoDoce = codigoDoce;
    }
}

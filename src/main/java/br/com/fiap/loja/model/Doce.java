package br.com.fiap.loja.model;

import java.time.LocalDate;

public class Doce {
    public Doce(int codigo, String nome, double valor, double peso, LocalDate dataValidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.peso = peso;
        this.dataValidade = dataValidade;
    }

    private int codigo;
    private String nome;
    private double valor;
    private double peso;
    private LocalDate dataValidade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}

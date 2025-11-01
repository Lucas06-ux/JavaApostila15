package br.com.fiap.dto.avaliacao;

public class MediaAvaliacaoDto {
    private int codigoDoce;
    private double mediaNota;
    private int quantidadeAvaliacoes;

    public MediaAvaliacaoDto(int codigoDoce, double mediaNota, int quantidadeAvaliacoes) {
        this.codigoDoce = codigoDoce;
        this.mediaNota = mediaNota;
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }

    public int getCodigoDoce() {
        return codigoDoce;
    }

    public void setCodigoDoce(int codigoDoce) {
        this.codigoDoce = codigoDoce;
    }

    public double getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(double mediaNota) {
        this.mediaNota = mediaNota;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setQuantidadeAvaliacoes(int quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }
}

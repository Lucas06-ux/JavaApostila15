package br.com.fiap.dto.exception;

public class ErroResponse {
    private String erro;
    private String detalhes;

    public ErroResponse(String erro, String detalhes) {
        this.erro = erro;
        this.detalhes = detalhes;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}

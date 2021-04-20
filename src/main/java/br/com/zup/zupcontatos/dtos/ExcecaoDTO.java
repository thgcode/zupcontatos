package br.com.zup.zupcontatos.dtos;

import br.com.zup.zupcontatos.exceptions.Erro;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ExcecaoDTO {
    HttpStatus status;
    private String tipo;
    private List<Erro> erros;

    public ExcecaoDTO() {

    }

    public ExcecaoDTO(HttpStatus status, String tipo, List<Erro> erros) {
        this.status = status;
        this.tipo = tipo;
        this.erros = erros;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Erro> getErros() {
        return erros;
    }

    public void setErros(List<Erro> erros) {
        this.erros = erros;
    }
}

package br.com.zup.zupcontatos.exceptions;

import org.springframework.http.HttpStatus;

public class ErroDoSistema extends RuntimeException{
    private HttpStatus status;
    private String tipo;

    public ErroDoSistema(HttpStatus status, String tipo, String mensagem) {
        super(mensagem);
        this.status = status;
        this.tipo = tipo;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }
}

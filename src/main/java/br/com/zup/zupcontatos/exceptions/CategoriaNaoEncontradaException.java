package br.com.zup.zupcontatos.exceptions;

import org.springframework.http.HttpStatus;

public class CategoriaNaoEncontradaException extends ErroDoSistema {
    public CategoriaNaoEncontradaException() {
        super(HttpStatus.PRECONDITION_FAILED, "categoria", "Categoria não encontrada");
    }
}

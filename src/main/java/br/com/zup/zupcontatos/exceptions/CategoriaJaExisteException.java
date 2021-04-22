package br.com.zup.zupcontatos.exceptions;

import org.springframework.http.HttpStatus;

public class CategoriaJaExisteException extends ErroDoSistema {
    public CategoriaJaExisteException() {
        super(HttpStatus.PRECONDITION_FAILED, "categoria", "Categoria já existe");
    }
}

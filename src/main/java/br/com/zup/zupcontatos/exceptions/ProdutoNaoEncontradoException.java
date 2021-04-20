package br.com.zup.zupcontatos.exceptions;

import org.springframework.http.HttpStatus;

public class ProdutoNaoEncontradoException {
    public ProdutoNaoEncontradoException() {
        super(HttpStatus.PRECONDITION_FAILED, "produto", "Produto não encontrado");
    }
}

package br.com.zup.zupcontatos.exceptions;

import org.springframework.http.HttpStatus;

public class ContatoNaoEncontradoException {
    public ContatoNaoEncontradoException() {
        super(HttpStatus.PRECONDITION_FAILED, "contato", "Contato não encontrado")
    }
}

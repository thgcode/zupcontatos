package br.com.zup.zupcontatos.exceptions;

import br.com.zup.zupcontatos.dtos.ExcecaoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ManipuladorDeExcecoes extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List <Erro> erros = new ArrayList <>();

        for (FieldError error : ex.getFieldErrors()) {
            erros.add(new Erro(error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(status).body(new ExcecaoDTO(status, "validacao", erros));
    }
}

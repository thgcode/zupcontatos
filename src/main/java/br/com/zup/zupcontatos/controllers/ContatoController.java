package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeContatoDTO;
import br.com.zup.zupcontatos.models.ContatoModel;
import br.com.zup.zupcontatos.services.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("contatos/")
public class ContatoController {
    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoModel cadastrarContato(@Valid @RequestBody CadastroDeContatoDTO dto) {
        return contatoService.cadastrarContato(dto.converterParaContato());
    }

    @GetMapping
    public Iterable <ContatoModel> listarContatos() {
        return contatoService.listarContatos();
    }

    @GetMapping("peloIdDoProduto/{id}/")
    public Iterable <ContatoModel> listarContatosPeloIdDoProduto(@PathVariable int id) {
        return contatoService.listarContatosPeloIdDoProduto(id);
    }

    @GetMapping("peloIdDaCategoria/{id}/")
    public Iterable <ContatoModel> listarPeloIdDaCategoria(@PathVariable int id) {
        return contatoService.listarContatosPeloIdDaCategoriaDoProduto(id);
    }
}

package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeProdutoDTO;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("produtos/")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel cadastrarProduto(@Valid @RequestBody CadastroDeProdutoDTO dto) {
        return produtoService.cadastrarProduto(dto.converterParaProduto());
    }

    @GetMapping("{nome}/")
    public ProdutoModel pesquisarProdutoPeloNome(@PathVariable String nome) {
        return produtoService.pesquisarProdutoPeloNome(nome);
    }

    @GetMapping
    public Iterable <ProdutoModel> listarProdutos() {
        return produtoService.listarProdutos();
    }
}

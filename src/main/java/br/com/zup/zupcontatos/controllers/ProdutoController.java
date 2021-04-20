package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeProdutoDTO;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.services.ProdutoService;
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
    public ProdutoModel cadastrarProduto(@Valid @RequestBody CadastroDeProdutoDTO dto) {
        return produtoService.cadastrarProduto(dto.converterParaProduto());
    }

    @GetMapping
    public Iterable <ProdutoModel> listarProdutos() {
        return produtoService.listarProdutos();
    }
}

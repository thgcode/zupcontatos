package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeCategoriaDTO;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias/")
public class CategoriaController {
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaModel cadastrarCategoria(@Valid @RequestBody CadastroDeCategoriaDTO dto) {
        return categoriaService.cadastrarCategoria(dto.converterParaCategoria());
    }

    @GetMapping("{nome}/")
    public CategoriaModel pesquisarCategoriaPeloNome(@PathVariable String nome) {
        return categoriaService.pesquisarCategoriaPeloNome(nome);
    }

    @GetMapping
    public Iterable <CategoriaModel> listarTodasAsCategorias() {
        return categoriaService.listarTodasAsCategorias();
    }
}

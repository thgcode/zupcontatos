package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeCategoriaDTO;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.services.CategoriaService;
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
    public CategoriaModel cadastrarCategoria(@Valid @RequestBody CadastroDeCategoriaDTO dto) {
        return categoriaService.cadastrarCategoria(dto.converterParaCategoria());
    }

    @GetMapping
    public Iterable <CategoriaModel> listarTodasAsCategorias() {
        return categoriaService.listarTodasAsCategorias();
    }
}

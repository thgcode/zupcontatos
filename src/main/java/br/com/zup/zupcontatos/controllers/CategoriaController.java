package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeCategoriaDTO;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.services.CategoriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return
    }
}

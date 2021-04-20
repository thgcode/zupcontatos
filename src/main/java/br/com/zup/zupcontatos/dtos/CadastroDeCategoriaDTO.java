package br.com.zup.zupcontatos.dtos;

import br.com.zup.zupcontatos.models.CategoriaModel;

import javax.validation.constraints.NotBlank;

public class CadastroDeCategoriaDTO {
    @NotBlank
    private String nome;

    public CadastroDeCategoriaDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaModel converterParaCategoria() {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNome(nome);
        return categoria;
    }
}

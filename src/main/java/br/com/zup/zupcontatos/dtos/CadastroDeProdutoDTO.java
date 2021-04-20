package br.com.zup.zupcontatos.dtos;

import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.models.ProdutoModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Arrays;

public class CadastroDeProdutoDTO {
    @NotBlank
    private String nome;

    @Positive
    private int categoriaId;

    public CadastroDeProdutoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public ProdutoModel converterParaProduto() {
        ProdutoModel produto = new ProdutoModel();
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(categoriaId);
        produto.setNome(nome);
        produto.setListaDeCategorias(Arrays.asList(categoria));
        return produto;
    }
}

package br.com.zup.zupcontatos.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    private List <CategoriaModel> listaDeCategorias;

    public ProdutoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List <CategoriaModel> getListaDeCategorias() {
        return listaDeCategorias;
    }

    public void setListaDeCategorias(List <CategoriaModel> listaDeCategorias) {
        this.listaDeCategorias = listaDeCategorias;
    }
}

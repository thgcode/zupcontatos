package br.com.zup.zupcontatos.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contatos")
public class ContatoModel {
    @Id
    private String email;

    @Column(unique = true)
    private String nomeCompleto;

    @ManyToMany
    private List<ProdutoModel> listaDeProdutos;

    public ContatoModel() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public List<ProdutoModel> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<ProdutoModel> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }
}

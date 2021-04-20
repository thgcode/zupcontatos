package br.com.zup.zupcontatos.dtos;

import br.com.zup.zupcontatos.models.ContatoModel;
import br.com.zup.zupcontatos.models.ProdutoModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class InscreverEmUmProdutoDTO {
    @NotBlank
    @Email
    private String emailContato;

    @Positive
    private int produtoId;

    public  InscreverEmUmProdutoDTO() {

    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public ContatoModel converterParaContato() {
        ContatoModel contato = new ContatoModel();

        contato.setEmail(emailContato);

        return contato;
    }

    public ProdutoModel converterParaProduto() {
        ProdutoModel produto = new ProdutoModel();

        produto.setId(produtoId);

        return produtoId;
    }
}

package br.com.zup.zupcontatos.dtos;

import br.com.zup.zupcontatos.models.ContatoModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastroDeContatoDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nomeCompleto;

    private String telefone;

    public CadastroDeContatoDTO() {

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ContatoModel converterParaContato() {
        ContatoModel contato = new ContatoModel();

        contato.setEmail(email);
        contato.setNomeCompleto(nomeCompleto);
        contato.setTelefone(telefone);

        return contato;
    }
}

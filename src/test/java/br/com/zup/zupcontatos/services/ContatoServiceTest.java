package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.ContatoModel;
import br.com.zup.zupcontatos.repositories.ContatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ContatoServiceTest {
    @Autowired
    private ContatoService contatoService;

    @MockBean
    private ContatoRepository contatoRepository;

    private ContatoModel contato;

    @BeforeEach
    public void setup() {
        contato = new ContatoModel();

        contato.setNomeCompleto("Teste");
        contato.setEmail("teste@teste.com");
    }

    @Test
    public void testarCadastrarContato() {
        Mockito.when(contatoRepository.save(Mockito.any(ContatoModel.class))).thenReturn(contato);

        ContatoModel contatoDoServico = contatoService.cadastrarContato(contato);

        Assertions.assertEquals(contato, contatoDoServico);
        Mockito.verify(contatoRepository, Mockito.times(1)).save(contato);
    }

    @Test
    public void testarListarContatos() {
        List<ContatoModel> contatos = Arrays.asList(contato);

        Mockito.when(contatoRepository.findAll()).thenReturn(contatos);

        Iterable <ContatoModel> contatosDoServico = contatoService.listarContatos();

        Assertions.assertEquals(contatos, contatosDoServico);
    }
}

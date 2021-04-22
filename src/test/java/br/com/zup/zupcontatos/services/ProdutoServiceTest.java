package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.repositories.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

@SpringBootTest
public class ProdutoServiceTest {
    @Autowired
    private ProdutoService produtoService;

    @MockBean
    private CategoriaService categoriaService;

    @MockBean
    private ProdutoRepository produtoRepository;

    private ProdutoModel produto;
    private CategoriaModel categoria;

    @BeforeEach
    public void setup() {
        categoria = new CategoriaModel();
        categoria.setId(1);

        produto = new ProdutoModel();
        produto.setNome("Teste");
        produto.setListaDeCategorias(Arrays.asList(categoria));
    }

    @Test
    public void testarCadastrarProduto() {
        Mockito.when(categoriaService.pesquisarCategoriaPeloId(Mockito.anyInt())).thenReturn(categoria);
        Mockito.when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produto);

        ProdutoModel produtoDoServico = produtoService.cadastrarProduto(produto);

        Assertions.assertEquals(produto, produtoDoServico);

        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);
    }
}

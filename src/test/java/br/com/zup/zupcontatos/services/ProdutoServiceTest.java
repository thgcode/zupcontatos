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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        List <CategoriaModel> listaDeCategorias = new ArrayList <>();
        listaDeCategorias.add(categoria);

        produto.setListaDeCategorias(listaDeCategorias);
    }

    @Test
    public void testarCadastrarProduto() {
        Mockito.when(categoriaService.pesquisarCategoriaPeloId(Mockito.anyInt())).thenReturn(categoria);
        Mockito.when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produto);

        ProdutoModel produtoDoServico = produtoService.cadastrarProduto(produto);

        Assertions.assertEquals(produto, produtoDoServico);

        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);
    }

    @Test
    public void testarAtualizarProdutoComNovaCategoria() {
        CategoriaModel categoria2 = new CategoriaModel();

        categoria2.setId(2);
        categoria2.setNome("Teste 2");

        categoria.setId(1);

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setId(1);
        produto2.setNome("Teste");

        produto2.setListaDeCategorias(Arrays.asList(categoria2));

        Mockito.when(categoriaService.pesquisarCategoriaPeloId(1)).thenReturn(categoria);
        Mockito.when(categoriaService.pesquisarCategoriaPeloId(2)).thenReturn(categoria2);
        Mockito.when(produtoRepository.findByNome("Teste")).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produto);

        ProdutoModel produtoDoServico = produtoService.cadastrarProduto(produto2);

        Assertions.assertEquals(2, produtoDoServico.getListaDeCategorias().size());
    }
    }
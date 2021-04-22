package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.repositories.CategoriaRepository;
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
public class CategoriaServiceTest {
    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    private CategoriaModel categoria;

    @BeforeEach
    public void setup() {
        categoria = new CategoriaModel();
        categoria.setNome("Teste");
    }

    @Test
    public void testarCriarCategoria() {
        Mockito.when(categoriaRepository.save(Mockito.any(CategoriaModel.class))).thenReturn(categoria);

        CategoriaModel categoriaDoServico = categoriaService.cadastrarCategoria(categoria);

        Mockito.verify(categoriaRepository , Mockito.times(1)).save(categoria);

        Assertions.assertEquals(categoria, categoriaDoServico);
    }

    @Test
    public void testarListarCategorias() {
        List<CategoriaModel> categorias = Arrays.asList(categoria);

        Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);

        Iterable <CategoriaModel> categoriasDoServico = categoriaService.listarTodasAsCategorias();

        Assertions.assertEquals(categorias, categoriasDoServico);
    }
}

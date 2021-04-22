package br.com.zup.zupcontatos.controllers;

import br.com.zup.zupcontatos.dtos.CadastroDeCategoriaDTO;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.services.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {
    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private CadastroDeCategoriaDTO cadastroDeCategoriaDTO;

    private CategoriaModel categoria;

    @BeforeEach
    public void setup() {
        cadastroDeCategoriaDTO = new CadastroDeCategoriaDTO();

        cadastroDeCategoriaDTO.setNome("Teste");

        categoria = new CategoriaModel();

        categoria.setId(1);
        categoria.setNome("Teste");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testarCadastrarCategoria() throws Exception{
        Mockito.when(categoriaService.cadastrarCategoria(Mockito.any(CategoriaModel.class))).thenReturn(categoria);

        String cadastroJson = objectMapper.writeValueAsString(cadastroDeCategoriaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testarCadastrarCategoriaComNomeEmBranco() throws Exception{
        cadastroDeCategoriaDTO.setNome(null);

        Mockito.when(categoriaService.cadastrarCategoria(Mockito.any(CategoriaModel.class))).thenReturn(categoria);

        String cadastroJson = objectMapper.writeValueAsString(cadastroDeCategoriaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cadastroJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}

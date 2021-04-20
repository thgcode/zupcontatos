package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.exceptions.ProdutoNaoEncontradoException;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(produto.getListaDeCategorias().get(0).getId());

        produto.getListaDeCategorias().set(0, categoria);

        return produtoRepository.save(produto);
    }

    public Iterable <ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel pesquisarProdutoPeloId(int id) {
        Optional <ProdutoModel> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            return optionalProduto.get();
        }

        throw new ProdutoNaoEncontradoException();
    }
}

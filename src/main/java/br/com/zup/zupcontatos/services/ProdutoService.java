package br.com.zup.zupcontatos.services;

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
        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(produto.getCategoria().getId());

        produto.setCategoria(categoria);

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

        throw new RuntimeException("Produto não encontrado");
    }
}

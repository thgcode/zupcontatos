package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.repositories.ProdutoRepository;

public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(produto.getCategoria().getId());

        produto.setCategoria(categoria;);

        return produtoRepository.save(produto);
    }

    public Iterable <ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }
}

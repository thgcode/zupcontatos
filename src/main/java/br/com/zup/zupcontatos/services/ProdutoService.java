package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.repositories.ProdutoRepository;

public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public Iterable <ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }
}

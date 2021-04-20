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
        Optional <ProdutoModel> optionalProduto = produtoRepository.findByNome(produto.getNome());

        if (optionalProduto.isPresent()) {
            return atualizarProdutoComNovaCategoria(optionalProduto.get(), produto);
        }

        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(produto.getListaDeCategorias().get(0).getId());

        produto.getListaDeCategorias().set(0, categoria);

        return produtoRepository.save(produto);
    }

    private boolean produtoJaTemACategoria(ProdutoModel produto, CategoriaModel categoria) {
        for (CategoriaModel categoriaPesquisar: produto.getListaDeCategorias()) {
            if (categoriaPesquisar.getId() == categoria.getId()) {
                return true;
            }
        }

        return false;
    }

    private ProdutoModel atualizarProdutoComNovaCategoria(ProdutoModel existente, ProdutoModel novaCategoria) {
        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(novaCategoria.getListaDeCategorias().get(0).getId());

        if (!produtoJaTemACategoria(existente, categoria)) {
            existente.getListaDeCategorias().add(categoria);

            return produtoRepository.save(existente);
        }

        return existente;
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

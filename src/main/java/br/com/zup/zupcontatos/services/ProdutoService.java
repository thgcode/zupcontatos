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

    /**
     * Cadastra um produto no sistema
     *
     * @param produto o produto a ser cadastrado
     *
     * @return o produto devidamente salvo no banco de dados
     */
    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        Optional <ProdutoModel> optionalProduto = produtoRepository.findByNome(produto.getNome());

        if (optionalProduto.isPresent()) {
            return atualizarProdutoComNovaCategoria(optionalProduto.get(), produto);
        }

        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(produto.getListaDeCategorias().get(0).getId());

        produto.getListaDeCategorias().set(0, categoria);

        return produtoRepository.save(produto);
    }

    /**
     * Verifica se o produto já está inserido numa determinada categoria
     *
     * @param produto o produto (do banco de dados) a verificar
     * @param categoria o id da categoria a verificar
     *
     * @return true se o produto está nessa categoria, false caso contrário
     */
    private boolean produtoJaTemACategoria(ProdutoModel produto, CategoriaModel categoria) {
        for (CategoriaModel categoriaPesquisar: produto.getListaDeCategorias()) {
            if (categoriaPesquisar.getId() == categoria.getId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Atualiza o produto com uma categoria nova
     *
     * @param existente o produto a ser atualizado
     * @param novaCategoria a categoria que vai ser inserida no produto
     *
     * @return o produto devidamente atualizado
     */
    private ProdutoModel atualizarProdutoComNovaCategoria(ProdutoModel existente, ProdutoModel novaCategoria) {
        CategoriaModel categoria = categoriaService.pesquisarCategoriaPeloId(novaCategoria.getListaDeCategorias().get(0).getId());

        if (!produtoJaTemACategoria(existente, categoria)) {
            existente.getListaDeCategorias().add(categoria);

            return produtoRepository.save(existente);
        }

        return existente;
    }

    /**
     * Lista os produtos cadastrados no sistema
     *
     * @return um interável contendo os produtos cadastrados
     */
    public Iterable <ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * Pesquisa um produto através do id, ou joga uma exceção caso não encontre
     *
     * @param id o id do produto a ser pesquisado
     *
     * @return objeto produto
     */
    public ProdutoModel pesquisarProdutoPeloId(int id) {
        Optional <ProdutoModel> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            return optionalProduto.get();
        }

        throw new ProdutoNaoEncontradoException();
    }
}

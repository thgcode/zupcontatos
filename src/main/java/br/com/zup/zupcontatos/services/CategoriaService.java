package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.exceptions.CategoriaJaExisteException;
import br.com.zup.zupcontatos.exceptions.CategoriaNaoEncontradaException;
import br.com.zup.zupcontatos.models.CategoriaModel;
import br.com.zup.zupcontatos.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Valida se uma categoria existe no sistema, lançando exceção caso não exista
     *
     * @param categoria objeto contendo o nome da categoria a ser validada
     */
    public void validarSeCategoriaJaExiste(CategoriaModel categoria) {
        Optional <CategoriaModel> optionalCategoria = categoriaRepository.findByNome(categoria.getNome());

        if (optionalCategoria.isPresent()) {
            throw new CategoriaJaExisteException();
        }
    }

    /**
     * Cadastra uma categoria no sistema
     *
     * @param categoria o objeto contendo a categoria a ser cadastrada
     * @return objeto categoria cadastrado
     */
    public CategoriaModel cadastrarCategoria(CategoriaModel categoria) {
        validarSeCategoriaJaExiste(categoria);

        return categoriaRepository.save(categoria);
    }

    /**
     * Lista todas as categorias presentes no sistema
     *
     * @return um iterável contendo todas as categorias cadastradas
     */
    public Iterable <CategoriaModel> listarTodasAsCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Pesquisa uma categoria com o seu id. Retorna exceção caso não achar
     *
     * @param id o id da categoria a ser pesquisada
     * @return um objeto categoria
     */
    public CategoriaModel pesquisarCategoriaPeloId(int id) {
        Optional <CategoriaModel> optionalCategoria = categoriaRepository.findById(id);

        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }

        throw new CategoriaNaoEncontradaException();
    }

    /**
     * Pesquisa uma categoria pelo nome
     *
     * @param nome o nome do produto a ser pesquisado
     * @return um objeto categoria
     */
    public CategoriaModel pesquisarCategoriaPeloNome(String nome) {
        Optional <CategoriaModel> optionalCategoria = categoriaRepository.findByNome(nome);

        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }

        throw new CategoriaNaoEncontradaException();
    }
}

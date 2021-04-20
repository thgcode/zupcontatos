package br.com.zup.zupcontatos.services;

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

    public CategoriaModel cadastrarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public Iterable <CategoriaModel> listarTodasAsCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional <CategoriaModel> pesquisarCategoriaPeloId(int id) {
        return categoriaRepository.findById(id);
    }
}

package br.com.zup.zupcontatos.repositories;

import br.com.zup.zupcontatos.models.CategoriaModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository <CategoriaModel, Integer> {
}

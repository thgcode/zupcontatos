package br.com.zup.zupcontatos.repositories;

import br.com.zup.zupcontatos.models.ProdutoModel;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository <ProdutoModel, Integer> {
}

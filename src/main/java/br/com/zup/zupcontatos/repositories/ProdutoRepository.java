package br.com.zup.zupcontatos.repositories;

import br.com.zup.zupcontatos.models.ProdutoModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProdutoRepository extends CrudRepository <ProdutoModel, Integer> {
    Optional<ProdutoModel> findByNome(String nome);
}

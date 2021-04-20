package br.com.zup.zupcontatos.repositories;

import br.com.zup.zupcontatos.models.ContatoModel;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository <ContatoModel, Integer> {
    Iterable <ContatoModel> findAllByListaDeProdutos_id(int id);
    Iterable <ContatoModel> findAllByListaDeProdutos_listaDeCategorias_id(int id);
}

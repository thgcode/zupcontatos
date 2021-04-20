package br.com.zup.zupcontatos.repositories;

import br.com.zup.zupcontatos.models.ContatoModel;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository <ContatoModel, Integer> {
}

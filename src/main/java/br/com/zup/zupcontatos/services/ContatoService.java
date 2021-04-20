package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.models.ContatoModel;
import br.com.zup.zupcontatos.repositories.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {
    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public ContatoModel cadastrarContato(ContatoModel contato) {
        return contatoRepository.save(contato);
    }

    public Iterable <ContatoModel> listarContatos() {
        return contatoRepository.findAll();
    }

    public ContatoModel pesquisarContatoPeloId(int id) {
        Optional<ContatoModel> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isPresent()) {
            return optionalContato.get();
        }

        throw new RuntimeException("Contato não encontrado");
    }
}

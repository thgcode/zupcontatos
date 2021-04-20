package br.com.zup.zupcontatos.services;

import br.com.zup.zupcontatos.exceptions.ContatoNaoEncontradoException;
import br.com.zup.zupcontatos.models.ContatoModel;
import br.com.zup.zupcontatos.models.ProdutoModel;
import br.com.zup.zupcontatos.repositories.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {
    private ContatoRepository contatoRepository;
    private ProdutoService produtoService;

    public ContatoService(ContatoRepository contatoRepository, ProdutoService produtoService) {
        this.contatoRepository = contatoRepository;
        this.produtoService = produtoService;
    }

    public ContatoModel cadastrarContato(ContatoModel contato) {
        return contatoRepository.save(contato);
    }

    public Iterable <ContatoModel> listarContatos() {
        return contatoRepository.findAll();
    }

    public ContatoModel pesquisarContatoPeloEmail(String email) {
        Optional<ContatoModel> optionalContato = contatoRepository.findById(email);

        if (optionalContato.isPresent()) {
            return optionalContato.get();
        }

        throw new ContatoNaoEncontradoException();
    }

    public Iterable <ContatoModel> listarContatosPeloIdDoProduto(int id) {
        return contatoRepository.findAllByListaDeProdutos_id(id);
    }

    public Iterable <ContatoModel> listarContatosPeloIdDaCategoriaDoProduto(int id) {
        return contatoRepository.findAllByListaDeProdutos_listaDeCategorias_id(id);
    }

    private boolean contatoTemOProduto(ContatoModel contato, ProdutoModel produto) {
        for (ProdutoModel produtoPesquisar: contato.getListaDeProdutos()) {
            if (produtoPesquisar.getId() == produto.getId()) {
                return true;
            }
        }

        return false;
    }

    private ContatoModel inscreve(ContatoModel contato, ProdutoModel produto) {
        contato.getListaDeProdutos().add(produto);
        return contatoRepository.save(contato);
    }

    private ContatoModel desinscreve(ContatoModel contato, ProdutoModel produto) {
        contato.getListaDeProdutos().remove(produto);

        return contatoRepository.save(contato);
    }

    public ContatoModel inscreverOuDesinscreverEmUmProduto(ContatoModel contato, ProdutoModel produto) {
        ContatoModel contatoExistente = pesquisarContatoPeloEmail(contato.getEmail());
        ProdutoModel produtoExistente= produtoService.pesquisarProdutoPeloId(produto.getId());

        if (contatoTemOProduto(contatoExistente, produtoExistente)) {
            return desinscreve(contatoExistente, produtoExistente);
        }

        return inscreve(contatoExistente, produtoExistente);
    }
}

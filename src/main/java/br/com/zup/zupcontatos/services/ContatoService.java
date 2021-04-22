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

    /**
     * Cadastra um contato no sistema
     *
     * @param contato o objeto do contato a ser cadastrado
     *
     * @return um objeto contato cadastrado
     */
    public ContatoModel cadastrarContato(ContatoModel contato) {
        return contatoRepository.save(contato);
    }

    /**
     * Lista todos os contatos cadastrados no sistema
     *
     * @return um iterável contendo todos os contatos cadastrados
     */
    public Iterable <ContatoModel> listarContatos() {
        return contatoRepository.findAll();
    }

    /**
     * Pesquisa um contato através do e-mail, ou lança uma exceção caso o e-mail do contato não exista
     *
     * @param email o email do contato a ser pesquisado
     *
     * @return um objeto contato
     */
    public ContatoModel pesquisarContatoPeloEmail(String email) {
        Optional<ContatoModel> optionalContato = contatoRepository.findById(email);

        if (optionalContato.isPresent()) {
            return optionalContato.get();
        }

        throw new ContatoNaoEncontradoException();
    }

    /**
     * Lista contatos passando um id de um prodto
     *
     * @param id o id do produto a pesquisar
     *
     * @return um iterável contendo os contatos que estão cadastrados naquele produto
     */
    public Iterable <ContatoModel> listarContatosPeloIdDoProduto(int id) {
        return contatoRepository.findAllByListaDeProdutos_id(id);
    }

    /**
     * Lista os contatos pelo id da categoria
     *
     * @param id o id da categoria
     *
     * @return um iterável contendo todos os contatos que estão cadastrados nessa categoria
     */
    public Iterable <ContatoModel> listarContatosPeloIdDaCategoriaDoProduto(int id) {
        return contatoRepository.findAllByListaDeProdutos_listaDeCategorias_id(id);
    }

    /**
     * Verifica se o contato está cadastrado ou não em determinado serviço
     *
     * @param contato o contato (do banco de dados) a verificar
     * @param produto pelo menos o id do produto a verificar
     *
     * @return true se o produto está cadastrado, false caso contrário
     */
    private boolean contatoTemOProduto(ContatoModel contato, ProdutoModel produto) {
        for (ProdutoModel produtoPesquisar: contato.getListaDeProdutos()) {
            if (produtoPesquisar.getId() == produto.getId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Inscreve o contato para receber notificações de umdeterminado produto
     *
     * @param contato o id do contato a inscrever
     * @param produto o id do produto a inscrever
     *
     * @return contato inscrito
     */
    private ContatoModel inscreve(ContatoModel contato, ProdutoModel produto) {
        contato.getListaDeProdutos().add(produto);
        return contatoRepository.save(contato);
    }

    /**
     * Desinscreve o contato de receber notificações do produto
     *
     * @param contato o contato que será desinscrito
     * @param produto o produto que o contato será desinscrito
     *
     * @return o contato desinscrito
     */
    private ContatoModel desinscreve(ContatoModel contato, ProdutoModel produto) {
        contato.getListaDeProdutos().remove(produto);

        return contatoRepository.save(contato);
    }

    /**
     * Inscreve o contato ou desinscreve de um produto
     *
     * @param contato o id do contato a inscrever ou desinscrever
     * @param produto o id do produto a inscrever ou desinscrever
     *
     * @return o contato com as modificações feitas
     */
    public ContatoModel inscreverOuDesinscreverEmUmProduto(ContatoModel contato, ProdutoModel produto) {
        ContatoModel contatoExistente = pesquisarContatoPeloEmail(contato.getEmail());
        ProdutoModel produtoExistente= produtoService.pesquisarProdutoPeloId(produto.getId());

        if (contatoTemOProduto(contatoExistente, produtoExistente)) {
            return desinscreve(contatoExistente, produtoExistente);
        }

        return inscreve(contatoExistente, produtoExistente);
    }

    /**
     * Remove um contato através do e-mail
     *
     * @param email o e-mail do contato a remover
     */
    public void removerContatoPeloEmail(String email) {
        pesquisarContatoPeloEmail(email);

        contatoRepository.deleteById(email);
    }
}

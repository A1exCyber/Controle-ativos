package com.enterprise.controle_ativos.service;

import com.enterprise.controle_ativos.model.Produto;
import com.enterprise.controle_ativos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Método Salvar:
    public void salvar(Produto produto) {
        // Se for um produto novo (ID nulo) e o status estiver vazio
        if (produto.getId() == null && (produto.getStatus() == null || produto.getStatus().isEmpty())) {
            produto.setStatus("Disponível");
        }
        repository.save(produto);
    }

    public Produto criarProduto(Produto produto) {
        produto.setStatus("disponivel");
        return repository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    public void alocarProduto(Long id, String nomeColaborador, String matricula, LocalDate data) {
        // Busca o produto (você já criou o buscarPorId!)
        Produto produto = buscarPorId(id);

        // Atualiza os campos com o que veio do formulário
        produto.setAlocadoPara(nomeColaborador);
        produto.setMatriculaColaborador(matricula);
        produto.setDataRetorno(data);
        produto.setStatus("Locado"); // Muda o status para o CSS ficar rosa/azul

        // DADO AUTOMÁTICO: Captura a data atual do servidor
        produto.setDataRetirada(LocalDate.now());

        // Salva no PostgreSQL
        repository.save(produto);
    }

    public void devolverProduto(Long id) {
        // Busca o produto (você já criou o buscarPorId!)
        Produto produto = buscarPorId(id);

        // Atualiza os campos
        produto.setAlocadoPara(null);
        produto.setMatriculaColaborador(null);
        produto.setDataRetorno(null);
        produto.setStatus("Disponível"); // Muda o status para o CSS ficar rosa/azul
        produto.setDataRetirada(null);

        // Salva no PostgreSQL
        repository.save(produto);
    }

    public List<Produto> buscarProdutos(String busca) {
        return repository.findByNomeContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(busca, busca);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public List<Produto> buscarPorNomeOuSerie(String termo) {
        // Aqui o Service usa o repository que já está injetado nele
        return repository.findByNomeContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(termo, termo);
    }

    public void excluirProduto(Long id) {
        // Busca o produto (você já criou o buscarPorId!)
        Produto produto = buscarPorId(id);
        // Salva no PostgreSQL
        repository.delete(produto);
    }

}

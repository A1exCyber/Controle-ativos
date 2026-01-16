package com.enterprise.controle_ativos.repository;

import com.enterprise.controle_ativos.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Aqui o Spring faz a mágica: ele cria o SAVE, DELETE e FIND sozinho!
    // Aqui O JPA dá save(), findById(), deleteById() e findAll().
    // No seu Repository
    List<Produto> findByNomeContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(String nome, String numeroSerie);
}

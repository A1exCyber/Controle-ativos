package com.enterprise.controle_ativos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // IMPORT CORRETO AQUI
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enterprise.controle_ativos.model.Produto;
import com.enterprise.controle_ativos.service.ProdutoService; // IMPORT DO SEU SERVICE

@Controller
public class AtivosController {

    // Precisamos declarar o Service para o Controller poder usá-lo
    private final ProdutoService produtoService;

    // Construtor para o Spring injetar o Service automaticamente
    public AtivosController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping({ "/", "/index" })
    public String index() {
        return "index";
    }

    @GetMapping("/cadastroProduto")
    public String cadastroProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastroProduto";
    }

    @GetMapping("/locarProduto/{id}")
    public String locarProduto(@PathVariable Long id, Model model) {
        // Agora o produtoService será reconhecido!
        Produto produto = produtoService.buscarPorId(id);

        // Agora o addAttribute funcionará com o import correto do Model
        model.addAttribute("produto", produto);

        return "locarProduto";
    }

}
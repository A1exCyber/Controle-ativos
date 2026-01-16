package com.enterprise.controle_ativos.controller;

import com.enterprise.controle_ativos.model.Produto;
import com.enterprise.controle_ativos.service.ProdutoService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {

    // 1. Declaramos o serviço como final
    private final ProdutoService produtoService;

    // 2. Injeção via construtor (O Spring faz o @Autowired automático aqui)
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "busca", required = false) String busca, Model model) {
        List<Produto> lista;

        if (busca != null && !busca.isEmpty()) {
            // Agora chamamos o SERVICE, não o repository
            lista = produtoService.buscarProdutos(busca);
        } else {
            lista = produtoService.listarTodos();
        }

        model.addAttribute("produtos", lista);
        return "home";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/produtos") // Note que agora é PostMapping, não Get!
    public String salvar(Produto produto) {
        // O Spring é inteligente: ele vê os campos "nome" e "numeroSerie" no HTML
        // e preenche automaticamente um objeto Produto para você!

        produtoService.salvar(produto); // Envia para o Service

        return "redirect:/home"; // Após salvar, ele te expulsa da página de cadastro e te joga na home
    }

    @PostMapping("/confirmarLocacao")
    public String confirmarLocacao(@RequestParam Long id, @RequestParam String alocadoPara,
            @RequestParam String matriculaColaborador, @RequestParam LocalDate dataRetorno) {

        // Esse print vai aparecer no seu terminal onde o Spring está rodando
        // System.out.println("ID recebido: " + id + " para o colaborador: " +
        // alocadoPara);

        produtoService.alocarProduto(id, alocadoPara, matriculaColaborador, dataRetorno);
        return "redirect:/home";
    }

    @GetMapping("/devolverProduto/{id}")
    public String devolverProduto(
            @PathVariable Long id) {

        produtoService.devolverProduto(id);
        return "redirect:/home";
    }

    @GetMapping("/excluirProduto/{id}")
    public String excluirProduto(
            @PathVariable Long id) {

        produtoService.excluirProduto(id);
        return "redirect:/home";
    }

    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        // Agora o produtoService será reconhecido!
        Produto produto = produtoService.buscarPorId(id);

        // Agora o addAttribute funcionará com o import correto do Model
        model.addAttribute("produto", produto);

        return "cadastroProduto";
    }

}
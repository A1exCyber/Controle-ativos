package com.enterprise.controle_ativos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.enterprise.controle_ativos.model.LoginDTO;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "index";
    }

    @PostMapping("/index")
    public String processarLogin(
            @ModelAttribute("loginDTO") LoginDTO loginDTO,
            Model model) {

        if ("admin".equals(loginDTO.getLogin()) &&
                "123".equals(loginDTO.getSenha())) {
            return "redirect:/home";
        }

        model.addAttribute("mensagem", "Login ou senha inválidos");
        return "index";
    }

}

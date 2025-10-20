package es.equiperocket.sci.controller;

import es.equiperocket.sci.model.Coordenador;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");

        if (coord == null) {
            // se não tiver logado, volta pro login
            return "redirect:/";
        }

        // adiciona o coordenador no model pra Thymeleaf usar
        model.addAttribute("coordenador", coord);
        return "home"; // carrega home.html
    }
}

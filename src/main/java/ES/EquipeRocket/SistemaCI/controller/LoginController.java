package ES.EquipeRocket.SistemaCI.controller;

import ES.EquipeRocket.SistemaCI.model.Coordenador;
import ES.EquipeRocket.SistemaCI.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String loginForm() {
        return "index"; // JSP ou Thymeleaf
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email,
                              @RequestParam String senha,
                              HttpSession session) {
        if (loginService.autenticar(email, senha)) {
            Coordenador coord = loginService.buscarCoordenadorPorEmail(email);
            session.setAttribute("coordenadorLogado", coord); // guarda na sessão
            return "redirect:/home";
        }
        return "redirect:/?erro=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
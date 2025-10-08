package ES.EquipeRocket.SistemaCI.security;

import ES.EquipeRocket.SistemaCI.model.Coordenador;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SessaoGlobal {

    @ModelAttribute("coordenador")
    public Coordenador coordenador(HttpSession session) {
        return (Coordenador) session.getAttribute("coordenadorLogado");
    }
}

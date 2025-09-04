package ES.EquipeRocket.SistemaCI.controller;

import ES.EquipeRocket.SistemaCI.dto.AlunoDTO;
import ES.EquipeRocket.SistemaCI.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping("/alunos")
    public String listarAlunos(Model model) {
        
        List<AlunoDTO> alunos = alunoService.listarAlunosDTO();
        model.addAttribute("alunos", alunos);
        
        return "alunos"; // nome do template Thymeleaf alunos.html
    }
}

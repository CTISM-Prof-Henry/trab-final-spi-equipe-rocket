package ES.EquipeRocket.SistemaCI.controller;

import ES.EquipeRocket.SistemaCI.dto.AlunoDTO;
import ES.EquipeRocket.SistemaCI.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/alunos")
    public String listarAlunos(Model model) {
        List<AlunoDTO> alunos = alunoService.listarAlunosDTO();
        model.addAttribute("alunos", alunos);
        return "alunos";
    }

    @GetMapping({"/alunos/curso/{idCurso}", "/aluno/curso/{idCurso}"})
    public String listarAlunosPorCurso(@PathVariable Long idCurso, Model model) {
        List<AlunoDTO> alunos = alunoService.listarAlunosPorCursoDTO(idCurso);
        model.addAttribute("alunos", alunos);
        return "alunos_curso"; // carrega o arquivo alunos_curso.html
    }

    // Mostra detalhes de um aluno específico (aceita plural e singular)
    @GetMapping({"/aluno/{matricula}", "/alunos/{matricula}"})
    public String mostrarAluno(@PathVariable Long matricula, Model model) {
        AlunoDTO alunoDTO = alunoService.buscarAlunoDTOPorMatricula(matricula);
        model.addAttribute("aluno", alunoDTO);
        return "aluno"; // Thymeleaf vai renderizar aluno.html
    }
}
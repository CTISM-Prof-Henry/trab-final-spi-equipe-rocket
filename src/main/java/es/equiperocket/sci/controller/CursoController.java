package es.equiperocket.sci.controller;

import es.equiperocket.sci.dto.CursoDTO;
import es.equiperocket.sci.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        List<CursoDTO> cursosDTO = cursoService.listarCursos()
                .stream()
                .map(CursoDTO::new)
                .toList();
        model.addAttribute("cursos", cursosDTO);
        return "cursos";
    }
}
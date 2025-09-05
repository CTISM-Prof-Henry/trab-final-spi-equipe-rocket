package com.EngenhariaSoftware.ModelagemBanco.controller;

import com.EngenhariaSoftware.ModelagemBanco.model.Curso;
import com.EngenhariaSoftware.ModelagemBanco.service.CursoService;
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
        List<Curso> cursos = cursoService.listarCursos();
        model.addAttribute("cursos", cursos);  // envia os dados para o Thymeleaf
        return "cursos"; // carrega cursos.html de templates/
    }
}

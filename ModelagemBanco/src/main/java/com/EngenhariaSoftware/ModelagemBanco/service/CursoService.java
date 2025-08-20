package com.EngenhariaSoftware.ModelagemBanco.service;

import com.EngenhariaSoftware.ModelagemBanco.model.Curso;
import com.EngenhariaSoftware.ModelagemBanco.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }
}

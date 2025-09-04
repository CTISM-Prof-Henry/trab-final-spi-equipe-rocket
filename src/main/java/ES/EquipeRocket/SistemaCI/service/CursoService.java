package ES.EquipeRocket.SistemaCI.service;

import ES.EquipeRocket.SistemaCI.model.Curso;
import ES.EquipeRocket.SistemaCI.repository.CursoRepository;
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

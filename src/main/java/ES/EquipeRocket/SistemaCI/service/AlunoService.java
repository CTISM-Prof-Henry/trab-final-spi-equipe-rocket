package ES.EquipeRocket.SistemaCI.service;

import ES.EquipeRocket.SistemaCI.dto.AlunoDTO;
import ES.EquipeRocket.SistemaCI.model.Aluno;
import ES.EquipeRocket.SistemaCI.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    public List<AlunoDTO> listarAlunosDTO() {
        List<Aluno> alunos = alunoRepository.findAll();
        
        return alunos.stream().map(aluno -> {
            String nomeCurso = "";
            Double evasao = 0.0;
            Map<String, Double> historicoEvasao = null;

            if (aluno.getMatriculas() != null && !aluno.getMatriculas().isEmpty()) {

                var matricula = aluno.getMatriculas().get(0);
                if (matricula.getCurso() != null) {
                    nomeCurso = matricula.getCurso().getNome();
                    evasao = matricula.getCurso().getEvasaoMedia() != null ?
                            matricula.getCurso().getEvasaoMedia() : 0.0;
                }
                historicoEvasao = matricula.getHistoricoEvasaoMap(); // pega o histórico
            }
            
            return new AlunoDTO(
                    aluno.getNome(),
                    aluno.getMatricula(),
                    evasao,
                    nomeCurso,
                    historicoEvasao
            );
        }).collect(Collectors.toList());
    }

    public List<AlunoDTO> listarAlunosPorCursoDTO(Long codigoCurso) {
        List<Aluno> alunos = alunoRepository.findByMatriculas_Curso_Codigo(codigoCurso);

        return alunos.stream().map(aluno -> {
            String nomeCurso = "";
            Double evasao = 0.0;
            Map<String, Double> historicoEvasao = null;

            if (aluno.getMatriculas() != null) {
                // Busca a matrícula que corresponde ao curso solicitado
                var matriculaEncontrada = aluno.getMatriculas().stream()
                        .filter(m -> m.getCurso() != null && m.getCurso().getCodigo().equals(codigoCurso))
                        .findFirst();

                if (matriculaEncontrada.isPresent()) {
                    var matricula = matriculaEncontrada.get();
                    var curso = matricula.getCurso();
                    nomeCurso = curso.getNome();
                    evasao = curso.getEvasaoMedia() != null ? curso.getEvasaoMedia() : 0.0;

                    // Pega o histórico de evasão da matrícula para o DTO
                    historicoEvasao = matricula.getHistoricoEvasaoMap();
                }
            }

            return new AlunoDTO(
                    aluno.getNome(),
                    aluno.getMatricula(),
                    evasao,
                    nomeCurso,
                    historicoEvasao
            );
        }).collect(Collectors.toList());
    }


    public AlunoDTO buscarAlunoDTOPorMatricula(Long matricula) {
        List<AlunoDTO> alunos = listarAlunosDTO();
        return alunos.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }
}

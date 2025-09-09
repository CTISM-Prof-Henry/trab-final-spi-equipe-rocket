package ES.EquipeRocket.SistemaCI.repository;

import ES.EquipeRocket.SistemaCI.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByMatriculas_Curso_Codigo(Long codigoCurso); // Testando
}
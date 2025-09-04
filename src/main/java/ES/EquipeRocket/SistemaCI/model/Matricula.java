package ES.EquipeRocket.SistemaCI.model;

import jakarta.persistence.*;

@Entity
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long codigo;
    
    private String historicoEvasao;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    
    public Matricula() {
    }
    
    public Matricula(String historicoEvasao, Aluno aluno, Curso curso) {
        this.historicoEvasao = historicoEvasao;
        this.aluno = aluno;
        this.curso = curso;
    }
    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getHistoricoEvasao() {
        return historicoEvasao;
    }
    
    public void setHistoricoEvasao(String historicoEvasao) {
        this.historicoEvasao = historicoEvasao;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
}

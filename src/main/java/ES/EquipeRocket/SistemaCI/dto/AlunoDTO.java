package ES.EquipeRocket.SistemaCI.dto;

import java.util.Map;

public class AlunoDTO {
    
    private String nome;
    private Long matricula;
    private Double evasao;
    private String nomeCurso;
    private Map<String, Double> historicoEvasao;  // novo campo

    public AlunoDTO() {}

    public AlunoDTO(String nome, Long matricula, Double evasao, String nomeCurso, Map<String, Double> historicoEvasao) {
        this.nome = nome;
        this.matricula = matricula;
        this.evasao = evasao;
        this.nomeCurso = nomeCurso;
        this.historicoEvasao = historicoEvasao;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Long getMatricula() { return matricula; }
    public void setMatricula(Long matricula) { this.matricula = matricula; }

    public Double getEvasao() { return evasao; }
    public void setEvasao(Double evasao) { this.evasao = evasao; }

    public String getNomeCurso() { return nomeCurso; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }

    public Map<String, Double> getHistoricoEvasao() { return historicoEvasao; }
    public void setHistoricoEvasao(Map<String, Double> historicoEvasao) { this.historicoEvasao = historicoEvasao; }
}

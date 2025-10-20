package es.equiperocket.sci.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.GenerationType;

import java.io.IOException;
import java.util.Map;

@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(columnDefinition = "jsonb") // Só para registro, depende do seu setup
    private String historico_evasao;  // JSON armazenado como String

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Transient // Este campo não é persistido no banco
    private Map<String, Double> hist_evasao_map;

    private static final ObjectMapper mapper = new ObjectMapper();

    public Matricula() {
    }

    public Matricula(Map<String, Double> hist_evasao_map, Aluno aluno, Curso curso) {
        this.setHistEvasaoMap(hist_evasao_map);
        this.aluno = aluno;
        this.curso = curso;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    // Getter e setter para a String JSON bruta (armazenamento no banco)
    public String getHistoricoEvasao() {
        return historico_evasao;
    }

    public void setHistoricoEvasao(String historico_evasao) {
        this.historico_evasao = historico_evasao;
        // Atualiza o Map sempre que a String JSON mudar
        this.hist_evasao_map = fromJson(historico_evasao);
    }

    // Getter e setter para o Map (usado no código)
    public Map<String, Double> getHistEvasaoMap() {
        // Se ainda não foi carregado, converte a string para Map
        if (hist_evasao_map == null && historico_evasao != null) {
            hist_evasao_map = fromJson(historico_evasao);
        }
        return hist_evasao_map;
    }

    public void setHistEvasaoMap(Map<String, Double> hist_evasao_map) {
        this.hist_evasao_map = hist_evasao_map;
        // Atualiza a string JSON sempre que o Map mudar
        this.historico_evasao = toJson(hist_evasao_map);
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

    // Converte String JSON para Map
    private Map<String, Double> fromJson(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Double>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Converte Map para String JSON
    private String toJson(Map<String, Double> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

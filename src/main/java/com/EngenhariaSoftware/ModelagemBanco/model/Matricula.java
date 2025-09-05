package com.EngenhariaSoftware.ModelagemBanco.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.Map;

@Entity
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(columnDefinition = "jsonb") // Só para registro, depende do seu setup
    private String historicoEvasao;  // JSON armazenado como String

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Transient // Este campo não é persistido no banco
    private Map<String, Double> historicoEvasaoMap;

    private static final ObjectMapper mapper = new ObjectMapper();

    public Matricula() {}

    public Matricula(Map<String, Double> historicoEvasaoMap, Aluno aluno, Curso curso) {
        this.setHistoricoEvasaoMap(historicoEvasaoMap);
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
        return historicoEvasao;
    }
    
    public void setHistoricoEvasao(String historicoEvasao) {
        this.historicoEvasao = historicoEvasao;
        // Atualiza o Map sempre que a String JSON mudar
        this.historicoEvasaoMap = fromJson(historicoEvasao);
    }

    // Getter e setter para o Map (usado no código)
    public Map<String, Double> getHistoricoEvasaoMap() {
        // Se ainda não foi carregado, converte a string para Map
        if (historicoEvasaoMap == null && historicoEvasao != null) {
            historicoEvasaoMap = fromJson(historicoEvasao);
        }
        return historicoEvasaoMap;
    }

    public void setHistoricoEvasaoMap(Map<String, Double> historicoEvasaoMap) {
        this.historicoEvasaoMap = historicoEvasaoMap;
        // Atualiza a string JSON sempre que o Map mudar
        this.historicoEvasao = toJson(historicoEvasaoMap);
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
            return mapper.readValue(json, new TypeReference<Map<String, Double>>() {});
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

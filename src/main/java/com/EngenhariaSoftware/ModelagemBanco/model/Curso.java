package com.EngenhariaSoftware.ModelagemBanco.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long codigo;
    
    private String nome;
    private Double evasaoMedia;
    
    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenador;
    
    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas;
    
    public Curso() {
    }
    
    public Curso(String nome, Double evasaoMedia, Coordenador coordenador) {
        this.nome = nome;
        this.evasaoMedia = evasaoMedia;
        this.coordenador = coordenador;
    }
    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Double getEvasaoMedia() {
        return evasaoMedia;
    }
    
    public void setEvasaoMedia(Double evasaoMedia) {
        this.evasaoMedia = evasaoMedia;
    }
    
    public Coordenador getCoordenador() {
        return coordenador;
    }
    
    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
    
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}

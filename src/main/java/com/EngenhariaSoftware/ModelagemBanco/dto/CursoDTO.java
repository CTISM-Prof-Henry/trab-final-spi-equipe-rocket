package com.EngenhariaSoftware.ModelagemBanco.dto;

import com.EngenhariaSoftware.ModelagemBanco.model.Curso;

public class CursoDTO {
    private Long codigo;
    private String nome;
    private Double evasaoMedia;

    public CursoDTO(Curso curso) {
        this.codigo = curso.getCodigo();
        this.nome = curso.getNome();
        this.evasaoMedia = curso.getEvasaoMedia();
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
}

package com.EngenhariaSoftware.ModelagemBanco.service;

import com.EngenhariaSoftware.ModelagemBanco.dto.AlunoDTO;
import com.EngenhariaSoftware.ModelagemBanco.model.Aluno;
import com.EngenhariaSoftware.ModelagemBanco.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

            if (!aluno.getMatriculas().isEmpty()) {
                var matricula = aluno.getMatriculas().get(0);
                if (matricula.getCurso() != null) {
                    nomeCurso = matricula.getCurso().getNome();
                    evasao = matricula.getCurso().getEvasaoMedia() != null ?
                            matricula.getCurso().getEvasaoMedia() : 0.0;
                }
            }

            return new AlunoDTO(
                    aluno.getNome(),
                    aluno.getMatricula(),
                    evasao,
                    nomeCurso
            );
        }).collect(Collectors.toList());
    }

}

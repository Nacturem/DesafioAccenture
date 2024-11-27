package com.example.Application.service;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import com.example.Application.repository.AlunoRepository;
import com.example.Application.repository.CursoRepository;
import com.example.Application.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InscricaoService {


    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;


    public List<Inscricao> findAll() {
        return inscricaoRepository.findAll();
    }

    public Inscricao salvarInscricao(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        // Verifica se o curso existe
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        // Verifica se já existe uma inscrição para esse aluno e curso
        boolean jaInscrito = inscricaoRepository.findByAlunoId(alunoId)
                .stream()
                .anyMatch(inscricao -> inscricao.getCurso().getId().equals(cursoId));

        if (jaInscrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno já está inscrito neste curso");
        }

        // Cria a inscrição
        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setAluno(aluno);
        novaInscricao.setCurso(curso);
        novaInscricao.setDataInscricao(LocalDate.now());

        return inscricaoRepository.save(novaInscricao);
    }

    public List<Curso> listarCursosPorAluno(Long alunoId) {
        return inscricaoRepository.findByAlunoId(alunoId)
                .stream()
                .map(Inscricao::getCurso)
                .collect(Collectors.toList());
    }
    // Listar alunos inscritos em um curso
    public List<Aluno> listarAlunosPorCurso(Long cursoId) {
        return inscricaoRepository.findByCursoId(cursoId)
                .stream()
                .map(Inscricao::getAluno)
                .collect(Collectors.toList());
    }

    public Inscricao salvarInscricao(Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }
}
















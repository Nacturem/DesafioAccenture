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

    public List<Curso> listarCursosPorAluno(Long alunoId) {
        List<Inscricao> inscricoes = inscricaoRepository.findByAlunoId(alunoId);
        return inscricoes.stream().map(Inscricao::getCurso).toList();
    }

    public List<Aluno> listarAlunosPorCurso(Long cursoId) {
        List<Inscricao> inscricoes = inscricaoRepository.findByCursoId(cursoId);
        return inscricoes.stream().map(Inscricao::getAluno).toList();
    }

    public Inscricao salvarInscricao(Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }
}
















package com.example.Application.service;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import com.example.Application.repository.CursoRepository;
import com.example.Application.repository.InscricaoRepository;
import com.example.Application.service.Exception.AlunoNaoEncontradoException;
import com.example.Application.service.Exception.CursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Application.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AlunoService {


    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;


    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarAlunosPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado"));
    }

    public Set<Curso> listarCursosDoAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado"));
        return aluno.getCursos();

    }
}

/*
    public Aluno findById(Long alunoId) {
        Optional<Aluno> obj = repository.findById(alunoId);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    public List<Curso> findCursosByAluno(Long alunoId) {
                Aluno aluno = findById(alunoId);
                List<Inscricao> inscricoes = inscricaoRepository.findByAlunoId(aluno.getId());
                return inscricoes.stream()
                        .map(Inscricao::getCurso)
                        .collect(Collectors.toList());
    }

    public Aluno insert(Aluno obj){
        return repository.save(obj);
    }

    public Aluno fromDto(AlunoDto objDto) {
        return new Aluno(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getDataCadastro());
    }
    */















package com.example.Application.service;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import com.example.Application.repository.CursoRepository;
import com.example.Application.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public Curso findById(Long id) {
        Optional<Curso> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    public Curso Insert(Curso obj) {
        return repository.save(obj);
    }

    public Curso fromDto(CursoDto objDto) {
        return new Curso(objDto.getId(), objDto.getCurso(), objDto.getDescricao(), objDto.getDataCriacao());
    }

    public List<Aluno> findAlunosByCurso(Long cursoId) {
        Curso curso = findById(cursoId);
        List<Inscricao> inscricoes = inscricaoRepository.findByCursoId(curso.getId());
        // Mapeia as inscrições para os alunos
        return inscricoes.stream()
                .map(Inscricao::getAluno)
                .collect(Collectors.toList());
    }
}








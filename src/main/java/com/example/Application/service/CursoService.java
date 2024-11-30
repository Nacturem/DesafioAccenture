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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;


    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso buscarCursosPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Curso não encontrado"));
    }

    public Set<Aluno> listarAlunosDoCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado"));
        return curso.getAlunos();

    }
}
/*



    public Curso fromDto(CursoDto objDto) {
        return new Curso(objDto.getId(), objDto.getCurso(), objDto.getDescricao(), objDto.getDataCriacao());
    }

*/






package com.example.Application.controller;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import com.example.Application.service.AlunoService;
import com.example.Application.service.CursoService;
import com.example.Application.service.Exception.AlunoNaoEncontradoException;
import com.example.Application.service.Exception.CursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos/{id}/alunos")
    public ResponseEntity<Set<Aluno>> alunosDoCurso(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursosPorId(id);
        return ResponseEntity.ok(curso.getAlunos());
    }
    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.salvar(curso);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cursoSalvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(cursoSalvo);
    }

    @GetMapping("/{cursosId}/alunos")
    public ResponseEntity<Set<Aluno>> listarAlunosDoCurso(@PathVariable Long cursoId) {
        try {
            Set<Aluno> alunos =  cursoService.listarAlunosDoCurso(cursoId);
            return ResponseEntity.ok(alunos);
        }catch (CursoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }


}

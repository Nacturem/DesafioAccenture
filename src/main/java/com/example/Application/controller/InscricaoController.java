package com.example.Application.controller;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.example.Application.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService service;


    @GetMapping
    public ResponseEntity <List<Inscricao>> findAll(){
     List<Inscricao> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/cursos/{alunoId}")
    public List<Curso> listarCursosPorAluno(@PathVariable Long alunoId) {
        return service.listarCursosPorAluno(alunoId);
    }

    @GetMapping("/alunos/{cursoId}")
    public List<Aluno> listarAlunosPorCurso(@PathVariable Long cursoId) {
        return service.listarAlunosPorCurso(cursoId);
    }
    @PostMapping
    public ResponseEntity<Inscricao> criarInscricao(@RequestBody Inscricao inscricao) {
        Inscricao novaInscricao = service.salvarInscricao(inscricao);
        return ResponseEntity.ok(novaInscricao);
    }


}

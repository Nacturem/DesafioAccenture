package com.example.Application.controller;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import com.example.Application.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CursoDto>> findAll() {
        List<Curso> list = service.findAll();
        List<CursoDto> listDto = list.stream().map(x -> new CursoDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<AlunoDto>> findAlunosByCurso(@PathVariable Long id) {
        List<Aluno> alunos = service.findAlunosByCurso(id);
        List<AlunoDto> alunosDto = alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(alunosDto);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody CursoDto objDto) {
        Curso obj = service.fromDto(objDto);
        obj = service.Insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}

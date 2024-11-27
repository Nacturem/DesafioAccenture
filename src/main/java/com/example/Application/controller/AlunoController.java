package com.example.Application.controller;


import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Application.service.AlunoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

     @Autowired
     private AlunoService service;

     @GetMapping
     public ResponseEntity<List<AlunoDto>> findAll() {
          List<Aluno> list = service.findAll();
          List<AlunoDto> listDto = list.stream().map(x -> new AlunoDto(x)).collect(Collectors.toList());
          return ResponseEntity.ok().body(listDto);
     }
     @GetMapping(value = "/{id}")
     public ResponseEntity<Aluno> findById(@PathVariable Long id) {
          Aluno obj = service.findById(id);
          return ResponseEntity.ok().body(obj);
     }

     @GetMapping("/{id}/cursos")
     public ResponseEntity<List<CursoDto>> findCursosByAluno(@PathVariable Long id) {
          List<Curso> cursos = service.findCursosByAluno(id);
          List<CursoDto> cursosDto = cursos.stream().map(CursoDto::new).collect(Collectors.toList());
          return ResponseEntity.ok().body(cursosDto);
     }

     @PostMapping
     public ResponseEntity<Void> insert(@RequestBody AlunoDto objDto) {
          Aluno obj = service.fromDto(objDto);
          obj = service.insert(obj);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
          return ResponseEntity.created(uri).build();
     }

}

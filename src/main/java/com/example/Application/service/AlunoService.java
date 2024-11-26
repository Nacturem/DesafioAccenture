package com.example.Application.service;

import com.example.Application.domain.Aluno;
import com.example.Application.dto.AlunoDto;
import com.example.Application.dto.CursoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Application.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {


    @Autowired
    private AlunoRepository repository;



    public List<Aluno> findAll(){
        return repository.findAll();

    }

    public Aluno findById(Long alunoId) {
        Optional<Aluno> obj = repository.findById(alunoId);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
    public Aluno insert(Aluno obj){
        return repository.save(obj);
    }

    public Aluno fromDto(AlunoDto objDto) {
        return new Aluno(objDto.getAlunoId(), objDto.getNome(), objDto.getEmail(), objDto.getDataCadastro());
    }

    }













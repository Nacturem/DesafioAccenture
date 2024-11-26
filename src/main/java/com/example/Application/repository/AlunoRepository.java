package com.example.Application.repository;

import com.example.Application.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    }




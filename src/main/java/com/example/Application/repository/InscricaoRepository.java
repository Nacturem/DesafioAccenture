package com.example.Application.repository;

import com.example.Application.domain.Aluno;
import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.example.Application.domain.InscricaoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoPK> {

    List<Inscricao> findByAlunoId(Long alunoId);
    List<Inscricao> findByCursoId(Long cursoId);


    // Buscar todas as inscrições de um aluno
    /*@Query("SELECT i FROM Inscricao i WHERE i.aluno.id = :alunoId")
    List<Inscricao> findByAlunoId(@Param("alunoId") Long alunoId);

    // Buscar todas as inscrições de um curso
    @Query("SELECT i FROM Inscricao i WHERE i.curso.id = :cursoId")
    List<Inscricao> findByCursoId(@Param("cursoId") Long cursoId);*/
}






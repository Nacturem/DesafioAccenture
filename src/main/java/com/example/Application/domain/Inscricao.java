package com.example.Application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tb_inscricao")
public class Inscricao implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private InscricaoPK id;

    @ManyToOne
    @MapsId("alunoId")
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInscricao;


    public Inscricao() {
    }

    public Inscricao(Aluno aluno, Curso curso, LocalDate dataInscricao) {
        this.id = new InscricaoPK(aluno.getAlunoId(), curso.getCursoId());
        this.aluno = aluno;
        this.curso = curso;
        this.dataInscricao = dataInscricao;
    }


    public InscricaoPK getId() {
        return id;
    }

    public void setId(InscricaoPK id) {
        this.id = id;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }


    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscricao inscricao = (Inscricao) o;
        return Objects.equals(id, inscricao.id) && Objects.equals(aluno, inscricao.aluno) && Objects.equals(curso, inscricao.curso) && Objects.equals(dataInscricao, inscricao.dataInscricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aluno, curso, dataInscricao);
    }
}


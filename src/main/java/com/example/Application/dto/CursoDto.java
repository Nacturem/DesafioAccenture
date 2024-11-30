package com.example.Application.dto;

import com.example.Application.domain.Curso;
import com.example.Application.domain.Inscricao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursoDto implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        private String nome;
        private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    @OneToMany(mappedBy = "curso")
    private List<Inscricao> inscricoes = new ArrayList<>();


    public CursoDto() {
    }

    public CursoDto(Curso obj) {
        id = obj.getId();
        nome = obj.getNome();
        descricao = obj.getDescricao();
        dataCriacao = obj.getDataCriacao();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }
}

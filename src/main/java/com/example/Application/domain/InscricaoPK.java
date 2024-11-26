package com.example.Application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

    @Embeddable
    public class InscricaoPK implements Serializable {
        private static final long serialVersionUID = 1L;


        private Long alunoId;
        private Long cursoId;


        public InscricaoPK() {
        }

        public InscricaoPK(Long alunoId, Long cursoId) {
            this.alunoId = alunoId;
            this.cursoId = cursoId;

        }

        public Long getAlunoId() {
            return alunoId;
        }

        public void setAlunoId(Long alunoId) {
            this.alunoId = alunoId;
        }

        public Long getCursoId() {
            return cursoId;
        }

        public void setCursoId(Long cursoId) {
            this.cursoId = cursoId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InscricaoPK that = (InscricaoPK) o;
            return Objects.equals(alunoId, that.alunoId) && Objects.equals(cursoId, that.cursoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(alunoId, cursoId);
        }
    }



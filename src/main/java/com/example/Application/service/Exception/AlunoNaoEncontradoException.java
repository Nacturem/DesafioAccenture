package com.example.Application.service.Exception;


public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(String message) {
        super(message);
    }
}
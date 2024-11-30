package com.example.Application.service.Exception;

public class CursoNaoEncontradoException extends RuntimeException {
    public CursoNaoEncontradoException(String message) {
        super(message);
    }
}
package br.com.pedrohfs.avaliacaoredemob.service.exceptions;

public class UsuarioCadastradoException extends RuntimeException{
    public UsuarioCadastradoException(){
        super("Usuário já cadastrado.");
    }
}

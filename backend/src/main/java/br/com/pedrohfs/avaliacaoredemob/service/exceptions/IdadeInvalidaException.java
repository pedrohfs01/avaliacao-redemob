package br.com.pedrohfs.avaliacaoredemob.service.exceptions;

public class IdadeInvalidaException extends RuntimeException{
    public IdadeInvalidaException(){
        super("Idade inválida. Necessita ser maior que 18 anos");
    }
}

package br.com.pedrohfs.avaliacaoredemob.service.exceptions;

public class SolicitacoesExcedidasException extends RuntimeException{
    public SolicitacoesExcedidasException(){
        super("Solicitações excedidas, apenas 2 por pessoa.");
    }
}

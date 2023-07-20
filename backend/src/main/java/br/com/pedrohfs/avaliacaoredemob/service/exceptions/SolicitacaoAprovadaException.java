package br.com.pedrohfs.avaliacaoredemob.service.exceptions;

public class SolicitacaoAprovadaException extends RuntimeException{
    public SolicitacaoAprovadaException(){
        super("Solicitação já aprovada.");
    }
}

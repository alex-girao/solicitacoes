package br.com.alexgirao.solicitacoes.exception;

public class SolicitacaoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8013456149613997159L;
	
	public SolicitacaoNotFoundException(String message) {
        super(message);
    }

}

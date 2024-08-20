package br.com.alexgirao.solicitacoes.common;

import br.com.alexgirao.solicitacoes.controller.request.SolicitacaoRequest;

public class SolicitacaoRequestConstants {
	
	public static final SolicitacaoRequest SOLICITACAO_REQUEST_VALIDA_MOCK = SolicitacaoRequest.builder()
		.texto("Problemas durante a consulta de saldo do cartão")
		.idTipoSolicitacao(1L)
		.build();
	
	public static final SolicitacaoRequest SOLICITACAO_REQUEST_INVALIDA_MOCK = SolicitacaoRequest.builder()
			.idTipoSolicitacao(1L)
			.build();

}

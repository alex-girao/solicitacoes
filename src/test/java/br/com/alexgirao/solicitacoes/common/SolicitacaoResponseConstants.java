package br.com.alexgirao.solicitacoes.common;

import br.com.alexgirao.solicitacoes.controller.response.SolicitacaoResponse;
import br.com.alexgirao.solicitacoes.controller.response.TipoSolicitacaoResponse;

public class SolicitacaoResponseConstants {
	
	public static final SolicitacaoResponse SOLICITACAO_RESPONSE_VALIDA_MOCK = 
				SolicitacaoResponse.builder()
					.texto("Problemas durante a consulta de saldo do cartão")
					.tipoSolicitacao(
						TipoSolicitacaoResponse.builder()
						.id(1L)
						.build()
					)
				.build();
	
}
package br.com.alexgirao.solicitacoes.common;

import java.util.Optional;

import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;

public class TipoSolicitacaoConstants {
	
	public static final Optional<TipoSolicitacao> TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK = 
			Optional.of(TipoSolicitacao.builder()
				.id(1L)
				.nome("Problemas com cartão")
				.build()
			);

}

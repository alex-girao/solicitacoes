package br.com.alexgirao.solicitacoes.common;

import br.com.alexgirao.solicitacoes.model.TimeAtendimento;

public class TimeAtendimentoConstants {
	
	public static final TimeAtendimento TIME_ATENDIMENTO_PROBLEMA_CARTAO_MOCK = TimeAtendimento.builder()
			.id(1L)
			.nome("Cartões")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.build();

}

package br.com.alexgirao.solicitacoes.common;

import java.util.Optional;

import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import br.com.alexgirao.solicitacoes.model.Solicitacao;

public class SolicitacaoConstants {

	public static final Solicitacao SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK = Solicitacao.builder()
			.texto("Problemas durante o desbloqueio do cartão")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)
			.build();
	
	public static final Solicitacao SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK = Solicitacao.builder()
			.id(2L)
			.texto("Problemas durante a consulta de saldo do cartão")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.EM_ATENDIMENTO)
			.atendente(AtentendeConstants.ATENDENTE_VALIDO_MOCK.get())
			.build();
	
	public static final Optional<Solicitacao> SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK = Optional.of(Solicitacao.builder()
			.id(1l)
			.texto("Problemas ao tentar trocar senha do cartão")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.EM_ATENDIMENTO)
			.atendente(AtentendeConstants.ATENDENTE_VALIDO_MOCK.get())
			.build());
	
	public static final Solicitacao SOLICITACAO_VALIDA_FINALIZADA_MOCK = Solicitacao.builder()
			.id(1l)
			.texto("Problemas ao tentar trocar senha do cartão")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.FINALIZADA)
			.atendente(AtentendeConstants.ATENDENTE_VALIDO_MOCK.get())
			.build();
	
	public static final Solicitacao SOLICITACAO_INVALIDA_MOCK = Solicitacao.builder()
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)
			.build();
	
	public static final Optional<Solicitacao> SOLICITACAO_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK = Optional.of(Solicitacao.builder()
			.id(2l)
			.texto("Problemas ao consultar extrato do cartão")
			.tipoSolicitacao(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.statusAtendimento(StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)
			.atendente(AtentendeConstants.ATENDENTE_VALIDO_MOCK.get())
			.build());
	
}

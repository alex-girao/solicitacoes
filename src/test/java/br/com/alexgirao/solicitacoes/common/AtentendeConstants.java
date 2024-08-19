package br.com.alexgirao.solicitacoes.common;

import java.util.Optional;

import br.com.alexgirao.solicitacoes.model.Atendente;

public class AtentendeConstants {
	
	public static final Optional<Atendente> ATENDENTE_VALIDO_MOCK = Optional.of(Atendente.builder()
			.id(1L)
			.nome("Gerusa Novaes")
			.timeAtendimento(TimeAtendimentoConstants.TIME_ATENDIMENTO_PROBLEMA_CARTAO_MOCK)
			.build());

}

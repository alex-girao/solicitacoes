package br.com.alexgirao.solicitacoes.controller.response;

import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitacaoResponse {
	
	private Long id;
	private String texto;
	private StatusAtendimentoEnum statusAtendimento;
	private TipoSolicitacaoResponse tipoSolicitacao;
	private AtendenteResponse atendente;
	
}

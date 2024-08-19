package br.com.alexgirao.solicitacoes.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoSolicitacaoResponse {

	private Long id;
	private String nome;
	
}

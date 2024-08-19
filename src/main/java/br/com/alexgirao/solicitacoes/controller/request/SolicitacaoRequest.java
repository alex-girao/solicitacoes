package br.com.alexgirao.solicitacoes.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alexgirao.solicitacoes.model.Solicitacao;
import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitacaoRequest {

	@NotEmpty(message = "{campo.texto.obrigatorio}")
	private String texto;
	
	@NotNull(message = "{campo.tipoSolicitacao.obrigatorio}")
    private Long idTipoSolicitacao;

	public Solicitacao toModel() {
		return Solicitacao.builder()
				.texto(texto)
				.tipoSolicitacao(
					TipoSolicitacao.builder()
					.id(idTipoSolicitacao)
					.build()
				)
				.build();
	}
    
}

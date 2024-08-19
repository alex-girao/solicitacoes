package br.com.alexgirao.solicitacoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alexgirao.solicitacoes.controller.response.TipoSolicitacaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Alex Girao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tipo_solicitacao")
public class TipoSolicitacao extends ApiModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	public TipoSolicitacaoResponse toResponse() {
		return TipoSolicitacaoResponse.builder()
				.id(id)
				.nome(nome)
				.build();
	}

}

package br.com.alexgirao.solicitacoes.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.alexgirao.solicitacoes.controller.response.SolicitacaoResponse;
import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Alex Girao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitacao")
public class Solicitacao extends ApiModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "texto", length = 1000, nullable = false)
	private String texto;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_atendimento", nullable = false)
	private StatusAtendimentoEnum statusAtendimento;

	@ManyToOne
	@JoinColumn(name = "id_tipo_solicitacao", nullable = false)
	private TipoSolicitacao tipoSolicitacao;

	@ManyToOne
	@JoinColumn(name = "id_atendente", nullable = true)
	private Atendente atendente;

	public SolicitacaoResponse toResponse() {
		return SolicitacaoResponse.builder()
				.id(id)
				.texto(texto)
				.statusAtendimento(statusAtendimento)
				.tipoSolicitacao(tipoSolicitacao.toResponse())
				.atendente(!Objects.isNull(atendente) ? atendente.toDTO() : null).build();
	}

	@Override
	public String toString() {
		return "Solicitacao [id=" + id + ", texto=" + texto + ", statusAtendimento=" + statusAtendimento
				+ ", tipoSolicitacao=" + tipoSolicitacao + ", atendente=" + (!Objects.isNull(atendente) ? atendente.getNome() : "") + "]";
	}
	
}

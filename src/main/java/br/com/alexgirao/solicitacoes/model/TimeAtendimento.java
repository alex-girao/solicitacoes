package br.com.alexgirao.solicitacoes.model;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

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
@Table(name = "time_atendimento", schema = "public")
public class TimeAtendimento extends ApiModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_solicitacao", nullable = false)
	private TipoSolicitacao tipoSolicitacao;

	@Override
	public String toString() {
		return "TimeAtendimento [id=" + id + ", nome=" + nome + ", tipoSolicitacao=" + (!Objects.isNull(tipoSolicitacao) ? tipoSolicitacao.getNome() : "") + "]";
	}
	
}

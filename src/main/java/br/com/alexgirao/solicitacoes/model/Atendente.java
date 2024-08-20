package br.com.alexgirao.solicitacoes.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Where;

import br.com.alexgirao.solicitacoes.controller.response.AtendenteResponse;


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
@Table(name = "atendente")
public class Atendente extends ApiModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_time_atendimento", nullable = false)
	private TimeAtendimento timeAtendimento;
	
	@OneToMany(mappedBy = "atendente")
	@Where(clause = "status_atendimento = 'EM_ATENDIMENTO'")
	private List<Solicitacao> solicitacoes;

	public AtendenteResponse toDTO() {
		return AtendenteResponse.builder()
				.nome(nome)
				.build();
	}

	@Override
	public String toString() {
		return "Atendente [id=" + id + ", nome=" + nome + ", timeAtendimento=" + (!Objects.isNull(timeAtendimento) ? timeAtendimento.getNome() : "") + "]";
	}

}

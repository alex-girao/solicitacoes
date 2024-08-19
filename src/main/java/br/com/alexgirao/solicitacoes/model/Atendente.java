package br.com.alexgirao.solicitacoes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.alexgirao.solicitacoes.controller.response.AtendenteResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 
 * @author Alex Girao
 */
@Data
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
	
	
}

package br.com.alexgirao.solicitacoes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class ApiModel {

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = true)
	private LocalDateTime dataAtualizacao;
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = LocalDateTime.now();
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = LocalDateTime.now();
	}
	
}

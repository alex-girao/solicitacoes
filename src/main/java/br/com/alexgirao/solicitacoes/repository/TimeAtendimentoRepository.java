package br.com.alexgirao.solicitacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexgirao.solicitacoes.model.TimeAtendimento;

public interface TimeAtendimentoRepository extends JpaRepository<TimeAtendimento, Long>{

}

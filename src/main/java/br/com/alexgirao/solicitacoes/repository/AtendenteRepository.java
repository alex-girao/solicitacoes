package br.com.alexgirao.solicitacoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alexgirao.solicitacoes.model.Atendente;
import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;

public interface AtendenteRepository extends JpaRepository<Atendente, Long>{
	
	@Query("SELECT a FROM Atendente a "
		+ "LEFT JOIN a.solicitacoes s "
		+ "WHERE a.timeAtendimento.tipoSolicitacao = :tipoSolicitacao")
	public List<Atendente> findByTipoSolicitacao(TipoSolicitacao tipoSolicitacao);
	
}

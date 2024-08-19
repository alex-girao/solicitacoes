package br.com.alexgirao.solicitacoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import br.com.alexgirao.solicitacoes.model.Solicitacao;
import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

	Optional<Solicitacao> findTop1ByTipoSolicitacaoAndStatusAtendimentoOrderByIdAsc(TipoSolicitacao tipoSolicitacao,
			StatusAtendimentoEnum aguardandoAtendimento);

}

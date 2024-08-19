package br.com.alexgirao.solicitacoes.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoNotFoundException;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoStatusAtendimentoException;
import br.com.alexgirao.solicitacoes.model.Atendente;
import br.com.alexgirao.solicitacoes.model.Solicitacao;
import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;
import br.com.alexgirao.solicitacoes.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private AtendenteService atendenteService;
	
	@Autowired
	private TipoSolicitacaoService tipoSolicitacaoService;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	public List<Solicitacao> buscarTodos() {
		return solicitacaoRepository.findAll();
	}

	public Solicitacao buscarPorId(Long id) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(
			()-> new SolicitacaoNotFoundException("Solicitação " + id + " não encontrada")
		);
		return solicitacao;
	}
	
	@Transactional
	public Solicitacao criar(Solicitacao request) {
		
		Optional<TipoSolicitacao> tipoSolicitacao = tipoSolicitacaoService.buscarPorId(request.getTipoSolicitacao().getId());
		Optional<Atendente> atendente = atendenteService.buscarProximoPorTipoSolicitacao(tipoSolicitacao.get());
		
		Solicitacao solicitacao = Solicitacao.builder()
				.texto(request.getTexto())
				.statusAtendimento(atendente.isPresent() ? StatusAtendimentoEnum.EM_ATENDIMENTO : StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)
				.atendente(atendente.isPresent() ? atendente.get() : null)
				.tipoSolicitacao(tipoSolicitacao.get())
				.build();
		
		return solicitacaoRepository.save(solicitacao);
	}

	@Transactional
	public Solicitacao finalizar(Long id) {
		
		List<Solicitacao> list = solicitacaoRepository.findAll();
		
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
		validarFinalizacao(solicitacao);
		atualizarStatus(solicitacao, StatusAtendimentoEnum.FINALIZADA);
		iniciarProxima(solicitacao.get().getAtendente());
		return solicitacao.get();
	}

	private void validarFinalizacao(Optional<Solicitacao> solicitacao) {
		if(!solicitacao.isPresent()) {
			throw new SolicitacaoNotFoundException("Solicitação não encontrada");
		}
		if(!solicitacao.get().getStatusAtendimento().equals(StatusAtendimentoEnum.EM_ATENDIMENTO)) {
			throw new SolicitacaoStatusAtendimentoException("Não é possível finalizar uma Solicitação que não está Em Atendimento");
		}
	}
	
	private void atualizarStatus(Optional<Solicitacao> solicitacao, StatusAtendimentoEnum statusAtendimento) {
		if(solicitacao.get().getStatusAtendimento().equals(statusAtendimento)) {
			throw new SolicitacaoStatusAtendimentoException("Não é possível alterar o Status de uma Solicitação para o mesmo Status");
		}
		solicitacao.get().setStatusAtendimento(statusAtendimento);
	}

	private void iniciarProxima(Atendente atendente) {
		Optional<Solicitacao> solicitacao = 
			solicitacaoRepository.findTop1ByTipoSolicitacaoAndStatusAtendimentoOrderByIdAsc(atendente.getTimeAtendimento().getTipoSolicitacao(), StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO);
		if(solicitacao.isPresent()) {
			solicitacao.get().setAtendente(atendente);
			solicitacao.get().setStatusAtendimento(StatusAtendimentoEnum.EM_ATENDIMENTO);
		}
	}
	
}

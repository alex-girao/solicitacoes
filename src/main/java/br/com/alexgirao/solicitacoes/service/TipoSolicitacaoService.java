package br.com.alexgirao.solicitacoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;
import br.com.alexgirao.solicitacoes.repository.TipoSolicitacaoRepository;

@Service
public class TipoSolicitacaoService {
	
	@Autowired
	private TipoSolicitacaoRepository tipoSolicitacaoRepository;

	public Optional<TipoSolicitacao> buscarPorId(Long id) {
		return tipoSolicitacaoRepository.findById(id);
	}

	public List<TipoSolicitacao> buscarTodos() {
		return tipoSolicitacaoRepository.findAll();
	}

}

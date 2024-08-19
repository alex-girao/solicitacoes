package br.com.alexgirao.solicitacoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexgirao.solicitacoes.model.Atendente;
import br.com.alexgirao.solicitacoes.model.TipoSolicitacao;
import br.com.alexgirao.solicitacoes.repository.AtendenteRepository;

@Service
public class AtendenteService {

	@Autowired
	private AtendenteRepository atendenteRepository;

	public Optional<Atendente> buscarProximoPorTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		List<Atendente> list = atendenteRepository.findByTipoSolicitacao(tipoSolicitacao);
		
		return list.stream().filter(s -> s.getSolicitacoes().size() < 3).findFirst();
	}

}

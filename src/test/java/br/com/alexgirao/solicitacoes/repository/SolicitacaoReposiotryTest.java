package br.com.alexgirao.solicitacoes.repository;

import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.alexgirao.solicitacoes.model.Solicitacao;

@DataJpaTest
public class SolicitacaoReposiotryTest {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void criarSolicitacao_ComDadosValidosSemAtendente() {
		Solicitacao solicitacao = solicitacaoRepository.save(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK);
		Solicitacao sut = testEntityManager.find(Solicitacao.class, solicitacao.getId());
		assertThat(sut).isNotNull();
		assertThat(sut.getTexto()).isEqualTo(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK.getTexto());
		assertThat(sut.getStatusAtendimento()).isEqualTo(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK.getStatusAtendimento());
		assertThat(sut.getTipoSolicitacao()).isEqualTo(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK.getTipoSolicitacao());
	}
	
	@Test
	public void criarSolicitacao_ComDadosValidosComAtendente() {
		Solicitacao solicitacao = solicitacaoRepository.save(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK);
		Solicitacao sut = testEntityManager.find(Solicitacao.class, solicitacao.getId());
		assertThat(sut).isNotNull();
		assertThat(sut.getTexto()).isEqualTo(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK.getTexto());
		assertThat(sut.getStatusAtendimento()).isEqualTo(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK.getStatusAtendimento());
		assertThat(sut.getTipoSolicitacao()).isEqualTo(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK.getTipoSolicitacao());
		assertThat(sut.getAtendente().getId()).isEqualTo(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK.getAtendente().getId());
	}
	
	@Test
	public void criarSolicitacao_ComDadosInvalidos_LancandoExcecao() {
		Solicitacao solicitacaoVazia = new Solicitacao();
		Solicitacao solicitacaoInvalida = Solicitacao.builder()
				.texto("")
				.build();
		
		assertThatThrownBy(() -> solicitacaoRepository.save(solicitacaoVazia));
		assertThatThrownBy(() -> solicitacaoRepository.save(solicitacaoInvalida));
		
	}
	
}

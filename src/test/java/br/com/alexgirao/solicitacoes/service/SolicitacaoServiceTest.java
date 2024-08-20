package br.com.alexgirao.solicitacoes.service;

import static br.com.alexgirao.solicitacoes.common.AtentendeConstants.ATENDENTE_VALIDO_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_INVALIDA_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_FINALIZADA_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK;
import static br.com.alexgirao.solicitacoes.common.TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alexgirao.solicitacoes.common.TipoSolicitacaoConstants;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoNotFoundException;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoStatusAtendimentoException;
import br.com.alexgirao.solicitacoes.model.Solicitacao;
import br.com.alexgirao.solicitacoes.repository.SolicitacaoRepository;

/**
 * Implementação de teste unitário utilizando Mockito
 */
@ExtendWith(MockitoExtension.class)
public class SolicitacaoServiceTest {
	
	@Mock
	private SolicitacaoRepository solicitacaoRepository;
	
	@Mock
	private TipoSolicitacaoService tipoSolicitacaoService;
	
	@Mock
	private AtendenteService atendenteService;
	
	@InjectMocks
	private SolicitacaoService solicitacaoService;
	
	@Test
	public void criarSolicitacao_ComDadosValidosSemAtendente_RetornandoSolicitacao() {
		when(tipoSolicitacaoService.buscarPorId(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK);
		when(solicitacaoRepository.save(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK)).thenReturn(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK);
		
		Solicitacao sut = solicitacaoService.criar(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK);
		assertThat(sut).isEqualTo(SOLICITACAO_VALIDA_SEM_ATENDENTE_MOCK);
	}
	
	@Test
	public void criarSolicitacao_ComDadosValidosComAtendente_RetornandoSolicitacao() {
		when(tipoSolicitacaoService.buscarPorId(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK);
		when(atendenteService.buscarProximoPorTipoSolicitacao(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())).thenReturn(ATENDENTE_VALIDO_MOCK);
		when(solicitacaoService.criar(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK)).thenReturn(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK);
		
		Solicitacao sut = solicitacaoService.criar(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK);
		assertThat(sut).isEqualTo(SOLICITACAO_VALIDA_COM_ATENDENTE_MOCK);
	}
	
	@Test
	public void criarSolicitacao_ComTipoSolicitacaoInvalida_LancandoExcecao() {
		when(tipoSolicitacaoService.buscarPorId(TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TipoSolicitacaoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK);
		when(solicitacaoRepository.save(SOLICITACAO_INVALIDA_MOCK)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy(() -> solicitacaoService.criar(SOLICITACAO_INVALIDA_MOCK)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void finalizarSolicitacao_ComDadosValidos_RetornandoSolicitacao() {
		when(solicitacaoRepository.findById(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK);
		
		Solicitacao sut = solicitacaoService.finalizar(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId());
		assertThat(sut).isEqualTo(SOLICITACAO_VALIDA_FINALIZADA_MOCK);
	}
	
	@Test
	public void finalizarSolicitacao_ComSolicitacaoInexistente_LancandoExcecao() {	
		assertThatThrownBy(() -> solicitacaoService.finalizar(999L)).isInstanceOf(SolicitacaoNotFoundException.class);
	}
	
	@Test
	public void finalizarSolicitacao_ComStatusAtendimentoInvalido_LancandoExcecao() {
		when(solicitacaoRepository.findById(SOLICITACAO_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK.get().getId())).thenReturn(SOLICITACAO_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK);
		
		assertThatThrownBy(() -> solicitacaoService.finalizar(SOLICITACAO_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK.get().getId())).isInstanceOf(SolicitacaoStatusAtendimentoException.class);
	}

}

package br.com.alexgirao.solicitacoes.controller;

import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoConstants.SOLICITACAO_VALIDA_FINALIZADA_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoRequestConstants.SOLICITACAO_REQUEST_INVALIDA_MOCK;
import static br.com.alexgirao.solicitacoes.common.SolicitacaoRequestConstants.SOLICITACAO_REQUEST_VALIDA_MOCK;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alexgirao.solicitacoes.enums.StatusAtendimentoEnum;
import br.com.alexgirao.solicitacoes.repository.SolicitacaoRepository;
import br.com.alexgirao.solicitacoes.service.SolicitacaoService;

@WebMvcTest(SolicitacaoController.class)
public class SolicitacaoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SolicitacaoService solicitacaoService;
	
	@MockBean
	private SolicitacaoRepository solicitacaoRepository;
	
	private final String PATH = "/solicitacao";
	
	@Test
		public void criarSolicitacao_ComDadosValidos_RetornandoCreated() throws Exception {
		when(solicitacaoService.criar(SOLICITACAO_REQUEST_VALIDA_MOCK.toModel())).thenReturn(SOLICITACAO_REQUEST_VALIDA_MOCK.toModel());
		mockMvc.perform(
				post(PATH)
				.content(objectMapper.writeValueAsString(SOLICITACAO_REQUEST_VALIDA_MOCK))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isCreated())
		;
		
	}
	
	@Test
	public void criarSolicitacao_ComDadosInvalidos_RetornandoBadRequest() throws Exception {
		mockMvc.perform(
				post(PATH)
				.content(objectMapper.writeValueAsString(SOLICITACAO_REQUEST_INVALIDA_MOCK))
				.contentType(MediaType.APPLICATION_JSON)
			)
		.andExpect(status().isBadRequest())
		;
	}
	
	@Test
	public void finalizarSolicitacao_ComStatusValido_ReturnsOk() throws Exception {
		when(solicitacaoService.criar(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get())).thenReturn(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get());
		
		when(solicitacaoRepository.findById(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK);
		
		when(solicitacaoRepository.findTop1ByTipoSolicitacaoAndStatusAtendimentoOrderByIdAsc(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getTipoSolicitacao(), StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)).thenReturn(Optional.empty());
		
		when(solicitacaoService.buscarPorId(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get());
		when(solicitacaoService.finalizar(SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(SOLICITACAO_VALIDA_FINALIZADA_MOCK);
		
		mockMvc.perform(
				patch(PATH+"/"+SOLICITACAO_VALIDA_EM_ATENDIMENTO_MOCK.get().getId()+"/finalizar")
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}
	
	

}

package br.com.alexgirao.solicitacoes.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alexgirao.solicitacoes.controller.request.SolicitacaoRequest;
import br.com.alexgirao.solicitacoes.controller.response.Response;
import br.com.alexgirao.solicitacoes.controller.response.ResponseStatusEnum;
import br.com.alexgirao.solicitacoes.controller.response.SolicitacaoResponse;
import br.com.alexgirao.solicitacoes.model.Solicitacao;
import br.com.alexgirao.solicitacoes.service.SolicitacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Alex Girao
 */
@RestController
@RequestMapping("/solicitacao")
@Api("Endpoint para Solicitação")
public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@GetMapping
	@ApiOperation("Lista todas as Solicitações")
	public ResponseEntity<Response<List<SolicitacaoResponse>>> buscarTodos(){
		Response<List<SolicitacaoResponse>> response = new Response<>();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setData(
			solicitacaoService.buscarTodos().stream().map(s -> s.toResponse()).collect(Collectors.toList())
		);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Detalhar Solicitação por Id")
	public ResponseEntity<Response<SolicitacaoResponse>> buscarPorId(@PathVariable Long id) {
		Response<SolicitacaoResponse> response = new Response<>();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setData(solicitacaoService.buscarPorId(id).toResponse());
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	@ApiOperation("Criar Solicitação")
	public ResponseEntity<Response<SolicitacaoResponse>> criar(@RequestBody @Valid SolicitacaoRequest form,
		UriComponentsBuilder uriBuilder) {
		
		Solicitacao solicitacao = solicitacaoService.criar(form.toModel());
		
		Response<SolicitacaoResponse> response = new Response<>();
		response.setData(solicitacao.toResponse());
		response.setMessage(Arrays.asList("Solicitação criada com sucesso."));
		response.setStatus(ResponseStatusEnum.SUCCESS);
		
		URI uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();
		return ResponseEntity.created(uri).body(response);		
	}
	
	@PatchMapping("/{id}/finalizar")
	@ApiOperation("Atualizar Solicitação por Id")
	public ResponseEntity<Response<SolicitacaoResponse>> finalizar(@PathVariable Long id) {
		Response<SolicitacaoResponse> response = new Response<>();
		response.setData(solicitacaoService.finalizar(id).toResponse());
		response.setMessage(Arrays.asList("Solicitação finalizada com sucesso."));
		response.setStatus(ResponseStatusEnum.SUCCESS);
		return ResponseEntity.ok(response);
	}
	
}

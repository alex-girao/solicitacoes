package br.com.alexgirao.solicitacoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexgirao.solicitacoes.controller.response.Response;
import br.com.alexgirao.solicitacoes.controller.response.ResponseStatusEnum;
import br.com.alexgirao.solicitacoes.controller.response.TipoSolicitacaoResponse;
import br.com.alexgirao.solicitacoes.service.TipoSolicitacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*
* @author Alex Girao
*/
@RestController
@RequestMapping("/tipoSolicitacao")
@Api("Endpoint para Tipo de Solicitação")
public class TipoSolicitacaoController {
	
	
	@Autowired
	private TipoSolicitacaoService tipoSolicitacaoService;
	
	@GetMapping
	@ApiOperation("Lista todas os Tipos de Solicitações")
	public ResponseEntity<Response<List<TipoSolicitacaoResponse>>> findAll(){
		Response<List<TipoSolicitacaoResponse>> response = new Response<>();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setData(
			tipoSolicitacaoService.buscarTodos().stream().map(s -> s.toResponse()).collect(Collectors.toList())
		);
		return ResponseEntity.ok(response);
	}

}

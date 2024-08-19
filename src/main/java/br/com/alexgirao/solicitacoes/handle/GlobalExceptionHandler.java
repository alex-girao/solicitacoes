package br.com.alexgirao.solicitacoes.handle;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alexgirao.solicitacoes.controller.response.Response;
import br.com.alexgirao.solicitacoes.controller.response.ResponseStatusEnum;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoNotFoundException;
import br.com.alexgirao.solicitacoes.exception.SolicitacaoStatusAtendimentoException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({SolicitacaoNotFoundException.class})
    public ResponseEntity<Response<?>> handleSolicitacaoNotFoundException(SolicitacaoNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Response.builder()
            		.message(Arrays.asList(exception.getMessage()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }
	
	@ExceptionHandler({SolicitacaoStatusAtendimentoException.class})
    public ResponseEntity<Response<?>> handleSolicitacaoStatusAtendimentoException(SolicitacaoStatusAtendimentoException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.builder()
            		.message(Arrays.asList(exception.getMessage()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.builder()
            		.message(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		BindingResult bindingResult = exception.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.builder()
            		.message(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }

}

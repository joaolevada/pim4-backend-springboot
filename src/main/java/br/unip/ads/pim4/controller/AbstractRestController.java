package br.unip.ads.pim4.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unip.ads.pim4.domain.DomainException;
import br.unip.ads.pim4.model.dto.ApiErrorDto;

@RestControllerAdvice
public abstract class AbstractRestController {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiErrorDto> handleUnknowException(RuntimeException ex) {
		String message = ex.getMessage().isEmpty() ? "Exceção sem mensagem" : ex.getMessage(); 
		return ResponseEntity.badRequest().body(new ApiErrorDto(message));
	}
	
	//@ExceptionHandler(Throwable.class)
	//public ResponseEntity<ApiErrorDto> handleThrowable(RuntimeException ex) {
	//	return ResponseEntity.badRequest().body(new ApiErrorDto(ex.getMessage()) );
	//}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ApiErrorDto> handleDomainException(DomainException ex) {
		return ResponseEntity.unprocessableEntity().body(new ApiErrorDto(ex.getMessage()) );
	}

	protected URI criarUriPorId(Object id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}

}

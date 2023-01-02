package med.voll.api.config.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ValidacaoForm {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratarErro404(){
		return ResponseEntity.notFound().build();
		                                         
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException exception){
		var erros = exception.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(ErrosDto::new).toList());
	}

}

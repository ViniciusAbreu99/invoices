package br.test.invoices.invoices.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.test.invoices.invoices.dtos.outputs.ErrorOutputDTO;

@RestControllerAdvice
public class FormErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorOutputDTO> handle(MethodArgumentNotValidException exception) {
		List<ErrorOutputDTO> errorsDTO = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(fieldError -> {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			ErrorOutputDTO errorDTO = new ErrorOutputDTO(fieldError.getField(), message);
			errorsDTO.add(errorDTO);
		});

		return errorsDTO;
	}
}

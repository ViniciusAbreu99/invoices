package br.test.invoices.invoices.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorOutputDTO {
	private String field;
	private String error;
}

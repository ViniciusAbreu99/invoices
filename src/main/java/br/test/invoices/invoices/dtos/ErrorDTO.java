package br.test.invoices.invoices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorDTO {

	private String field;
	private String description;
}

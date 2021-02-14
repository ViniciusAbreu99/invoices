package br.test.invoices.invoices.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CompaniesByRegimeOutputDTO {
	private Integer countCompanies;
	private String taxRegime;
}

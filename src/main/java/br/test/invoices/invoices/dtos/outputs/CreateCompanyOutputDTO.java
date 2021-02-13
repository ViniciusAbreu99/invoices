package br.test.invoices.invoices.dtos.outputs;

import java.util.List;

import br.test.invoices.invoices.dtos.ErrorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreateCompanyOutputDTO {

	private Boolean success;
	private List<ErrorDTO> errors;
}

package br.test.invoices.invoices.dtos.outputs;

import br.test.invoices.invoices.enums.TaxRegime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaxRegimeType {

	private Integer code;
	private String description;
	
	public TaxRegimeType(TaxRegime taxRegime) {
		this.code = taxRegime.getCode();
		this.description = taxRegime.getDescription();
	}
}

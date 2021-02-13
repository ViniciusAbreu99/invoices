package br.test.invoices.invoices.dtos.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import br.test.invoices.invoices.entities.Company;
import br.test.invoices.invoices.enums.TaxRegime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreateCompanyInputDTO {
	
	@NotBlank
	private String companyName;
	
	@NotBlank @CNPJ
	private String cnpj;
	
	@NotNull
	private Integer taxRegime;
	
	@NotBlank
	private String email;
	
	public Company getCompany() {
		Company company = new Company();
		company.setCnpj(this.cnpj);
		company.setCompanyName(this.companyName);
		company.setEmail(this.email);
		company.setTaxRegime(TaxRegime.getByCode(this.taxRegime));
		company.setEmail(this.email);
		
		return company;
	}
}

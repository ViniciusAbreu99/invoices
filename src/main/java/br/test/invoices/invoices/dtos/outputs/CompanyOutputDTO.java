package br.test.invoices.invoices.dtos.outputs;

import br.test.invoices.invoices.entities.Company;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompanyOutputDTO {
	private String companyName;
	private String cnpj;
	private String taxRegime;
	private String email;
	
	public CompanyOutputDTO(Company company) {
		this.companyName = company.getCompanyName();
		this.cnpj = company.getCnpj();
		this.email = company.getEmail();
		this.taxRegime = company.getTaxRegime().getDescription();
	}
}

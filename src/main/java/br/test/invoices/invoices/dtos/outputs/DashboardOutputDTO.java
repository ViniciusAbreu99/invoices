package br.test.invoices.invoices.dtos.outputs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.test.invoices.invoices.entities.Company;
import br.test.invoices.invoices.enums.TaxRegime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DashboardOutputDTO {
	private Integer totalCompanies;
	private List<CompaniesByRegimeOutputDTO> companiesByRegime;
	
	public DashboardOutputDTO(List<Company> companies) {
		companiesByRegime = new ArrayList<>();
		
		setTotalCompanies(companies.size());
		
		Map<TaxRegime, List<Company>> groupedCompanies = companies.stream().collect(Collectors.groupingBy(company -> company.getTaxRegime()));
		for (Map.Entry<TaxRegime, List<Company>> item: groupedCompanies.entrySet()) {
			companiesByRegime.add(new CompaniesByRegimeOutputDTO(item.getValue().size(), item.getKey().getDescription()));
		}
	}
}

package br.test.invoices.invoices.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.test.invoices.invoices.entities.Company;

@Repository
public interface CompanyRepositoryCustom {
	
	public List<Company> findAllByFilters(String cnpjOrName, Integer taxRegime);
}

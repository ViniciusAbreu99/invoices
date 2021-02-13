package br.test.invoices.invoices.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.test.invoices.invoices.entities.Company;

@Repository
public interface CompanyRepositoryCustom {
	
	public Page<Company> findAllByFilters(String cnpjOrName, Integer taxRegime, Pageable pageable);
}

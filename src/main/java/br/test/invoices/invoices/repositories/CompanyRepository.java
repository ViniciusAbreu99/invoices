package br.test.invoices.invoices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.test.invoices.invoices.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>, CompanyRepositoryCustom {

	boolean existsByCnpj(String cnpj);

	boolean existsByEmail(String email);

}

package br.test.invoices.invoices.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.test.invoices.invoices.entities.Company;
import br.test.invoices.invoices.enums.TaxRegime;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Company> findAllByFilters(String cnpjOrName, Integer taxRegime) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);

		Root<Company> company = criteriaQuery.from(Company.class);
		List<Predicate> predicates = new ArrayList<>();

		if (taxRegime != null)
			predicates.add(criteriaBuilder.equal(company.get("taxRegime"), TaxRegime.getByCode(taxRegime)));

		if (StringUtils.isNotBlank(cnpjOrName))
			predicates.add(criteriaBuilder.or(
					criteriaBuilder.like(company.get("companyName"), "%" + cnpjOrName + "%"),
					criteriaBuilder.like(company.get("cnpj"), "%" + cnpjOrName + "%")));

		criteriaQuery.orderBy(criteriaBuilder.asc(company.get("companyName")));
		criteriaQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Company> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}
}

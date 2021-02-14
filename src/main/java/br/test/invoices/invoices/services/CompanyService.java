package br.test.invoices.invoices.services;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.test.invoices.invoices.dtos.ErrorDTO;
import br.test.invoices.invoices.dtos.inputs.CreateCompanyInputDTO;
import br.test.invoices.invoices.dtos.outputs.CompanyOutputDTO;
import br.test.invoices.invoices.dtos.outputs.CreateCompanyOutputDTO;
import br.test.invoices.invoices.dtos.outputs.DashboardOutputDTO;
import br.test.invoices.invoices.dtos.outputs.TaxRegimeType;
import br.test.invoices.invoices.entities.Company;
import br.test.invoices.invoices.enums.TaxRegime;
import br.test.invoices.invoices.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public CreateCompanyOutputDTO createCompany(@Valid CreateCompanyInputDTO createCompanyInputDTO) {
		CreateCompanyOutputDTO createCompanyOutputDTO = validCompany(createCompanyInputDTO);
		if (createCompanyOutputDTO.getSuccess()) {
			companyRepository.save(createCompanyInputDTO.getCompany());
		}
		
		return createCompanyOutputDTO;
	}

	private CreateCompanyOutputDTO validCompany(@Valid CreateCompanyInputDTO createCompanyInputDTO) {
		CreateCompanyOutputDTO createCompanyOutputDTO = new CreateCompanyOutputDTO();
		createCompanyOutputDTO.setSuccess(true);
		createCompanyOutputDTO.setErrors(new ArrayList<ErrorDTO>());
		
		
		if(!isCnpjValid(createCompanyInputDTO.getCnpj())) {
			createCompanyOutputDTO.setSuccess(false);
			createCompanyOutputDTO.getErrors().add(new ErrorDTO("cnpj", "Este cnpj já está cadastrado."));
		}
		
		if(!isEmailValid(createCompanyInputDTO.getEmail())) {
			createCompanyOutputDTO.setSuccess(false);
			createCompanyOutputDTO.getErrors().add(new ErrorDTO("email", "Este endereço de email já está em uso."));
		}
		
		return createCompanyOutputDTO;
	}

	private boolean isCnpjValid(String cnpj) {
		return companyRepository.existsByCnpj(cnpj) == false;
	}

	private boolean isEmailValid(String email) {
		return companyRepository.existsByEmail(email) == false;
	}
	
	public List<CompanyOutputDTO> searchCompaniesByFilters(String cnpjOrName, Integer taxRegime, Pageable pageable) {

		Page<Company> companies = companyRepository.findAllByFilters(cnpjOrName, taxRegime, pageable);

		return companies.stream().map(CompanyOutputDTO::new).collect(Collectors.toList());
	}

	public Boolean deleteById(Integer id) {

		if (!companyRepository.existsById(id))
			return false;

		companyRepository.deleteById(id);
		return true;
	}

	public List<TaxRegimeType> getAllTaxRegimeTypes() {
		return Arrays.asList(TaxRegime.values()).stream().map(TaxRegimeType::new).collect(Collectors.toList());
	}

	public DashboardOutputDTO getDashboard() {
		List<Company> companies = companyRepository.findAll();
		
		return new DashboardOutputDTO(companies);
	}
}

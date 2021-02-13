package br.test.invoices.invoices.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.test.invoices.invoices.dtos.inputs.CreateCompanyInputDTO;
import br.test.invoices.invoices.dtos.outputs.CompanyOutputDTO;
import br.test.invoices.invoices.dtos.outputs.CreateCompanyOutputDTO;
import br.test.invoices.invoices.dtos.outputs.TaxRegimeType;
import br.test.invoices.invoices.services.CompanyService;

@RestController
@RequestMapping("companies")
public class CompaniesController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("create")
	@ResponseBody
	public ResponseEntity<CreateCompanyOutputDTO> createCompany(
			@RequestBody @Valid CreateCompanyInputDTO createCompanyInputDTO) {

		CreateCompanyOutputDTO createCompanyOutputDTO = companyService.createCompany(createCompanyInputDTO);

		if (createCompanyOutputDTO.getSuccess())
			return new ResponseEntity<CreateCompanyOutputDTO>(createCompanyOutputDTO, HttpStatus.CREATED);

		return new ResponseEntity<>(createCompanyOutputDTO, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("search")
	public ResponseEntity<List<CompanyOutputDTO>> searchCompanies(@RequestParam(required = false) String cnpjOrName,
			@RequestParam(required = false) Integer taxRegime,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		List<CompanyOutputDTO> companiesDTO = companyService.searchCompaniesByFilters(cnpjOrName, taxRegime, pageable);

		return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Integer id) {

		Boolean success = companyService.deleteById(id);

		if (success)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>("Não foi possível deletar empresa.", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("types")
	public ResponseEntity<List<TaxRegimeType>> getTypes() {
		return new ResponseEntity<>(companyService.getAllTaxRegimeTypes(), HttpStatus.OK);
	}
}

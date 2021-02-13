package br.test.invoices.invoices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.test.invoices.invoices.enums.TaxRegime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Setter
	private String companyName;
	
	@Setter
	@Column(unique = true)
	private String cnpj;
	
	@Setter
	@Enumerated(EnumType.STRING)
	private TaxRegime taxRegime;
	
	@Setter
	@Column(unique = true)
	private String email;
}

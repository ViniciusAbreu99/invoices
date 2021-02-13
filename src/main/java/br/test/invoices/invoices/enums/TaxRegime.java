package br.test.invoices.invoices.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaxRegime {
	SIMPLE_NATIONAL(1, "Simples Nacional"),
	PRESUMED_PROFIT(2, "Lucro Presumido");
	
	private Integer code;
	private String description;
	
	public static TaxRegime getByCode(Integer code) {
		for (TaxRegime value : values()) {
			if(value.getCode() == code) 
				return value;
		}
		
		return null;
	}
	
	public static TaxRegime getByDescription(String description) {
		for (TaxRegime value : values()) {
			if(value.getDescription().equals(description)) 
				return value;
		}
		
		return null;
	}
}

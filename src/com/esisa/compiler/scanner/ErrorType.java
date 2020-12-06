package com.esisa.compiler.scanner;

public enum ErrorType {
	LEXICAL_ERROR("Erreur Lexicale"),
	SYNTAXE_ERROR("Erreur suntaxique"),
	SEMANTIC_ERROR("Erreur Sematique");
	
	private String value;
	
	private ErrorType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

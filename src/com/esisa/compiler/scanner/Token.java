package com.esisa.compiler.scanner;

public class Token {
	private String value;
	private String type;
	
	public Token() {
		
	}

	public String toString() {
		return "[" + value + " : " + type + "]";
	}

	public Token(String value, String type) {
		super();
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

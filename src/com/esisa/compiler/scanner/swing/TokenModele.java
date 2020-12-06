package com.esisa.compiler.scanner.swing;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.esisa.compiler.scanner.Token;

public class TokenModele extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private Vector<Token> tokens;

	public TokenModele() {
		super(new String[] {"Lexeme", "Unite Lexicale"}, 0);
		tokens = new Vector<>();
	}
	
	public void add(Token token) {
		tokens.add(token);
		addRow(new String [] {token.getValue(), token.getType()});
	}
	
	public void setTokens(Vector<Token> tokens) {
		setRowCount(0);
		tokens.clear();
		for (Token token : tokens) {
			add(token);
		}
	}
	
	public Vector<Token> getTokens() {
		return tokens;
	}
	
	public Token getToken(int index) {
		return tokens.get(index);
	}
	
	public void clear() {
		setRowCount(0);
		tokens.clear();
	}

}

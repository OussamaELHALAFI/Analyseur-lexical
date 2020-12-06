package com.esisa.compiler.scanner;

import java.util.Vector;


import com.esisa.compiler.scanner.dfa.ArithmeticOperator;
import com.esisa.compiler.scanner.dfa.Assignment;
import com.esisa.compiler.scanner.dfa.Blank;
import com.esisa.compiler.scanner.dfa.Comment;
import com.esisa.compiler.scanner.dfa.Identier;
import com.esisa.compiler.scanner.dfa.LitteralString;
import com.esisa.compiler.scanner.dfa.Number;
import com.esisa.compiler.scanner.dfa.Seperator;

public class Scanner {
	public static final Token EOF = new Token("$", "$");
	
	private Vector<DFA> list;
	private InputTabe inputTape;
	private Vector<String> ignored;
	private Vector<ReserveWorldList> reserveWords;
	
	private Vector<Token> tokens; // table des symbole
	private Vector<Error> errors;
	
	public Scanner() {
		ignored = new Vector<String>();
		list = new Vector<>();
		reserveWords = new Vector<>();
		add(new Identier());
		add(new Number());
		add(new Comment());
		add(new ArithmeticOperator());
		add(new Blank());
		add(new Seperator());
		add(new Assignment());
		add(new LitteralString());
		
		addReserveWordList("type", "byte", "short", "int", "long", "float", "double", "char", "boolean");
		addReserveWordList("modifier", "private", "public", "protected", "static", "final", "synchronized");
		addReserveWordList("keyword", "package", "class", "interface", "enum", "import", "void", "new", "instanceof", "return", "for", "if", "else", "do", "while");
	}
	
	public void setIgnoredTokens(String ...tokens) {
		for (String token : tokens) {
			ignored.add(token);
		}
	}
	
	private boolean toIgnored(Token token) {
		for (String ign : ignored) {
			if (ign.equals(token.getType())) {
				return true;
			}
		}
		return false;
	}
	
	public void addReserveWordList(ReserveWorldList list) {
		reserveWords.add(list);
	}
	
	public void addReserveWordList(String name, String ...words) {
		reserveWords.add(new ReserveWorldList(name, words));
	}
	
	public boolean checkReservedWord(Token token) {
		if(!token.getType().contentEquals("id")) return false;
		for (ReserveWorldList list : reserveWords) {
			if(list.contains(token.getValue())) {
				token.setType(list.getName());
				return true;
			}
		}
		return false;
	}
	
	public void scan(InputTabe inputTape) {
		this.inputTape = inputTape;
		tokens = new Vector<>();
		errors = new Vector<>();
		Error.reset();
		
		Token token = next();
		while (token != EOF) {
			if(token != null) {
				if (!toIgnored(token)) tokens.add(token);
			}
			token = next();
		}
	}
	
	public void add(DFA a) {
		list.add(a);
	}
	
	public Token next() {
		if (inputTape.eof()) {
			return EOF;
		}
		for (DFA a : list) {
			Token token = a.extract(inputTape); 
			if (token != null) {
				checkReservedWord(token);
				return token;
			}
		}
		
		Error error = new Error(/*ErrorType.LEXICAL_ERROR,inputTape.getReadHeader(),"" + inputTape.current(),"Symbole inconu"*/);
		errors.add(error);
		inputTape.next();
		
		return null;
	}
	
	public Vector<Token> getTokens() {
		return tokens;
	}
	
	public Vector<Error> getErrors() {
		return errors;
	}

}

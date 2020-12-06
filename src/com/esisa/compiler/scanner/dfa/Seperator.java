package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Seperator extends DFA {

	public Seperator() {
		super("Sep", 2, 1);
		add(0, 1, "(){}[],.:;");
	}

}

package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Assignment extends DFA {

	public Assignment() {
		super("assignment", 2, 1);
		add(0, 1, '=');
	}

}

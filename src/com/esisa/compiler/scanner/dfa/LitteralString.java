package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class LitteralString extends DFA {

	public LitteralString() {
		super("litteral", 4, 2);
		add(0, 1, '"');
		add(1, 2, '"');
		add(1, 3, '\\');
		
		add(1, 1, 1, '"' - 1);
		add(1, 1, '"' + 1, '\\' - 1);
		add(1, 1, '\\' + 1, 255);

		add(3, 1, 1, 255);
	}

	

}

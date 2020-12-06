package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class ArithmeticOperator extends DFA {

	public ArithmeticOperator() {
		super("AO" ,2 ,1);
		add(0, 1, "+-*/");
	}

}

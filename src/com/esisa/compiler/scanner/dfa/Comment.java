package com.esisa.compiler.scanner.dfa;

import com.esisa.compiler.scanner.DFA;

public class Comment extends DFA {

	public Comment() {
		super("comment", 6, 4);
		add(0, 1, '/');
		add(1, 2, '*');
		add(2, 3, '*');
		
		add(2, 2, 1, '/' - 1);
		add(2, 1, '*' + 1, 255);
		
		add(3, 4, '/');
		add(3, 3, '*');
		
		add(3, 2, 1, '*' - 1);
		add(3, 2, '*' + 1, '/' - 1);
		add(3, 2, '*' + 1, 255);
		
		add(5, 5, 1, 9);
		add(5, 5, 11, 12);
		add(5, 5, 14, 255);
		
		add(5, 4, "\n\r");
	}

}

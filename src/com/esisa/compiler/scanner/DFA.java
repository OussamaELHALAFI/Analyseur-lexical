package com.esisa.compiler.scanner;

public class DFA {
	private String name;
	private State E[];
	private Transition T;
	
	public DFA(String name, int stateCount, int ...accepting) {
		this.name = name;
		T = new Transition(stateCount);
		E = new State[stateCount];
		for (int i = 0; i < E.length; i++) {
			E[i] = new State(i);
		}
		for (int e : accepting) {
			E[e].setAccepting(true);
		}
	}

	public void add(int e1, int e2, int s) {
		T.add(e1, e2, s);
	}

	public void add(int e1, int e2, int min, int max) {
		T.add(e1, e2, min, max);
	}

	public void add(int e1, int e2, String set) {
		T.add(e1, e2, set);
	}
	
	public Token extract(InputTabe inputTape) {
		int e = 0;
		inputTape.mark();
		char s = inputTape.current();
		while (T.next(e, s) != -1) {
			e = T.next(e, s);
			s = inputTape.next();
		}
		if (E[e].isAccepting()) {
			String token = inputTape.getToken();
			return new Token(token, name);
		}
		else {
			inputTape.reset();
			return null;
		}
	}

}

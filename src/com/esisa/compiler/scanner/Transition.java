package com.esisa.compiler.scanner;

public class Transition {
	private int T[][];
	
	public Transition(int stateCount) {
		T = new int[stateCount][256];
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < 256; j++) {
				T[i][j] = -1;
			}
		}
	}
	
	public void add(int e1, int e2, int s) { // la transition de e1 vers e2 sur s
		T[e1][s] = e2;
	}

	public void add(int e1, int e2, int min, int max) {
		for (int s = min; s <= max; s++) {
			T[e1][s] = e2;
		}
	}
	public void add(int e1, int e2, String set) {
		for (int i = 0; i < set.length(); i++) {
			T[e1][set.charAt(i)] = e2;
		}
	}
	
	public int next(int source, int s) {
		return T[source][s];
	}

}

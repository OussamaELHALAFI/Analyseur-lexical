package com.esisa.compiler.scanner;

public class State {
	private int id;
	private boolean accepting;
	
	public State() {
		id = 0;
		accepting = false;
	}

	public String toString() {
		if (accepting) {
			return "((" + id + "))";
		}
		else {
			return " (" + id + ") ";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAccepting() {
		return accepting;
	}

	public void setAccepting(boolean accepting) {
		this.accepting = accepting;
	}

	public State(int id) {
		this.id = id;
		this.accepting = false;
	}
	
	public State(int id, boolean accepting) {
		this.id = id;
		this.accepting = accepting;
	}

}

package com.esisa.compiler.scanner;

import java.util.Vector;

import com.esisa.compiler.scanner.dfa.Identier;
import com.esisa.compiler.scanner.dfa.Number;

public class ScannerTest {
	
	
	public ScannerTest() {
		exp06();
	}
	
	void exp01() {
		State e0 = new State();
		State e1 = new State(1, true);
		System.out.println(e0);
		System.out.println(e1);
	}
	
	void exp02() {
		Transition id = new Transition(2);
		
		id.add(0, 1, 'a', 'z');
		id.add(0, 1, 'A', 'Z');
		id.add(0, 1, '_');
		
		System.out.println(id.next(0, 'x'));
		System.out.println(id.next(0, '7'));
	}
	
	void exp03() {
		DFA a = new DFA("id", 2, 1);
		a.add(0, 1, 'a', 'z');
		a.add(0, 1, 'A', 'Z');
		a.add(0, 1, '_');
		a.add(1, 1, 'a', 'z');
		a.add(1, 1, 'A', 'Z');
		a.add(1, 1, '_');
		a.add(1, 1, '0', '9');
		
		//System.out.println(a.extract(new InputTabe("alpha23 = 43.654;")));equivalent
		InputTabe it = new InputTabe("alpha23 = 43.654;");
		Token token = a.extract(it);
		System.out.println(token);
	}
	
	void exp04() {
		DFA a = new DFA("nb", 4, 1, 3);
		a.add(0, 1, '0', '9');
		a.add(0, 2, '.');
		a.add(1, 1, '0', '9');
		a.add(1, 3, '.');
		a.add(2, 3, '0', '9');
		a.add(3, 3, '0', '9');

		System.out.println(a.extract(new InputTabe("alpha23 = 43.654;")));
	}
	
	void exp05() {
		Identier id = new Identier();
		Number nb = new Number();
		
		InputTabe it = new InputTabe("alpha23 = 43.654;");
		Token token = id.extract(it);
		System.out.println(token);
	}
	
	void exp06() {
		InputTabe it = new InputTabe("alpha23 = 43.654 * beta / 2.5 - x2;");
		Scanner scanner = new Scanner();
		scanner.setIgnoredTokens("blank");
		scanner.scan(it);
		Vector<Token> tokens =  scanner.getTokens();
		Vector<Error> errors = scanner.getErrors();
		
		System.out.println(">> Table des symboles : ");
		System.out.println("------------------------");
		for (Token token : tokens) {
			System.out.println(token);
		}
		
		System.out.println(">> Erreur Lexicales : " + Error.getErrorCounter());
		System.out.println("------------------------");
		for (Error error : errors) {
			System.out.println(error);
		}
	}

	public static void main(String[] args) {
		new ScannerTest();
	}
	
}

package com.esisa.compiler.scanner;

import java.util.Vector;

public class ReserveWorldList {
		private String name;
		private Vector<String> list;

	public ReserveWorldList(String name, String ...words) {
		this.name = name;
		list = new Vector<>();
		for (String word : words) {
			list.add(word);
		}
	}
	
	public void add(String word) {
		list.add(word);
	}
	
	public boolean contains(String token) {
		return list.contains(name);
	}
	
	public String getName() {
		return name;
	}

}

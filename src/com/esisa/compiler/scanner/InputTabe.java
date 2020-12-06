package com.esisa.compiler.scanner;

public class InputTabe {
	private String buffer;
	private int readHeader = 0;
	private int start = 0;
	
	public InputTabe(String buffer) {
		this.buffer = buffer;
	}
	
	public void mark() {
		start = readHeader;
	}
	
	public void reset() {
		readHeader = start;
	}
	public char next() {
		readHeader++;
		if (eof()) return 0;
		return buffer.charAt(readHeader);
	}
	
	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public int getReadHeader() {
		return readHeader;
	}

	public void setReadHeader(int readHeader) {
		this.readHeader = readHeader;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public char current() {
		return buffer.charAt(readHeader);
	}
	
	public boolean eof() {
		return readHeader >= buffer.length();
	}
	
	public String getToken() {
		String token = buffer.substring(start, readHeader);
		return token;
	}
	
}

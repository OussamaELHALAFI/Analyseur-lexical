package com.esisa.compiler.scanner.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CellProperties {
	public static final int CENTER = JLabel.CENTER;
	public static final int LEFT = JLabel.LEFT;
	public static final int RIGHT = JLabel.RIGHT;
	private Color textColor = Color.BLACK;
	private Color backColor = Color.white;
	private Font font = new Font("", Font.PLAIN, 12);
	private int align = LEFT;

	public CellProperties() {
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public CellProperties(Color textColor) {
		super();
		this.textColor = textColor;
	}

	public CellProperties(Color textColor, Color backColor) {
		super();
		this.textColor = textColor;
		this.backColor = backColor;
	}

	public CellProperties(Color textColor, Color backColor, int align) {
		super();
		this.textColor = textColor;
		this.backColor = backColor;
		this.align = align;
	}

	public CellProperties(Color textColor, Color backColor, Font font, int align) {
		super();
		this.textColor = textColor;
		this.backColor = backColor;
		this.font = font;
		this.align = align;
	}

	public CellProperties(Color textColor, Font font, int align) {
		super();
		this.textColor = textColor;
		this.font = font;
		this.align = align;
	}

	public CellProperties(Color backColor, int align) {
		super();
		this.backColor = backColor;
		this.align = align;
	}

}

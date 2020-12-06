package com.esisa.compiler.scanner.swing;

import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TokenCellRenderer implements TableCellRenderer {
	public static final Hashtable<String, CellProperties> properties = new Hashtable<>();
	
	static {
		properties.put("id", new CellProperties(Color.black));
		properties.put("type", new CellProperties(Color.blue));
		properties.put("keyword", new CellProperties(Color.magenta));
		properties.put("string", new CellProperties(Color.red));
		properties.put("nb", new CellProperties(Color.orange, CellProperties.RIGHT));
		properties.put("operator", new CellProperties(Color.DARK_GRAY));
		properties.put("Seperator", new CellProperties(new Color(0, 128, 0)));
	}
	
	public TokenCellRenderer() {
		
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		
		TokenTable t = (TokenTable)table;
		
		JLabel l1 = new JLabel("" + value);
		CellProperties props = properties.get(t.getToken(row).getType());
		if(props != null) {
			l1.setForeground(props.getTextColor());
			l1.setOpaque(true);
			l1.setBackground(props.getBackColor());
			l1.setFont(props.getFont());
			l1.setHorizontalAlignment(props.getAlign());
		}
		return l1;
	}

}

package com.esisa.compiler.scanner.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;

import com.esisa.compiler.scanner.InputTabe;
import com.esisa.compiler.scanner.Scanner;
import com.esisa.compiler.scanner.Token;
import com.esisa.compiler.scanner.Error;
import com.esisa.compiler.scanner.ErrorType;

public class ScannerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Scanner scanner;
	private JTextArea source;
	private TokenTable table;
	private DefaultTableModel tokenModel;
	private DefaultListModel<Error> errorModel;
	private JList<Error> list;
	private JLabel result;
	private JFileChooser fileChooser;
	private File currentfile = null;
	
	public ScannerPanel(Scanner scanner) {
		this.scanner = scanner;
		setLayout(new BorderLayout());
		
		source = new JTextArea();
		//add("Center", new JScrollPane(source));
		source.setFont(new Font("Console", Font.PLAIN, 16));
		source.setForeground(Color.BLUE);
		source.setMargin(new Insets(10, 10, 10, 10));
		
		tokenModel = new DefaultTableModel(new String[] {"Lexeme", "Unite Lexicale"}, 0);
		JTable table = new JTable(tokenModel);
		//add("East", new JScrollPane(table));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.add(new JScrollPane(source));
		splitPane.add(new JScrollPane(table));
		add("Center", splitPane);
		splitPane.setDividerLocation(500);
		
		result = new JLabel();
		result.setForeground(Color.BLUE);
		result.setFont(new Font("Cursive", Font.BOLD, 14));
		
		errorModel = new DefaultListModel<>();
		list = new JList<Error>(errorModel);
		list.setFont(new Font("Console", Font.PLAIN, 16));
		list.setForeground(Color.RED);
		
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add("North", result);
		p1.add("Center", new JScrollPane(list));
		
		add("South", p1);
		
		createToolbar();
		
		fileChooser = new JFileChooser();
	}
	
	public void createToolbar() {
		JToolBar toolbar = new JToolBar();
		add("North", toolbar);
		String [][] tools = { 
				{"Nouveau", "new.png"},
				{"Ouvrir", "open.png"},
				{"Enregistrer", "save.png"},
				{"Enregistrer sous", "saveas.png"},
				{"Analyse Lexicale", "scan.png"},
				{"-"},
				{"Copier", "copy.gif"},
				{"Couper", "cut.gif"},
				{"Coller", "paste.gif"},
		};
		for (int i = 0; i < tools.length; i++) {
			if ("-".equals(tools[i][0])) {
				toolbar.addSeparator();
			}
			else {
				ImageIcon icon = new ImageIcon("resources/icons/" + tools[i][1]);
				JButton btn = new JButton(icon);
				btn.setToolTipText(tools[i][0]);
				btn.addActionListener(this);
				toolbar.add(btn);
			}
		}
	}
	
	public void open() {
		int res = fileChooser.showOpenDialog(this);
		if (res == JFileChooser.APPROVE_OPTION) {
			try {
				File f = fileChooser.getSelectedFile();
				FileReader in = new FileReader(f);
				char t[] = new char[(int)f.length()];
				in.read();
				in.close();
				
				source.setText(new String(t));
			}
			catch (Exception e) {
				result.setText("Erreur : " + e.getMessage());
			}
		}
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton src = (JButton)e.getSource();
			if(src.getToolTipText().equals("Analyse Lexicale")) {
				scan();
			}
			else if (src.getToolTipText().equals("Copier")) {
				source.copy();
			}
			else if (src.getToolTipText().equals("Couper")) {
				source.cut();
			}
			else if (src.getToolTipText().equals("Coller")) {
				source.paste();
			}
			else if (src.getToolTipText().equals("Nouveau")) {
				newFile();
			}
			else if (src.getToolTipText().equals("Ouvrir")) {
				open();
			}
			else if (src.getToolTipText().equals("Enregistrer")) {
				saveTokens("resources/tokens/");
			}
		}
	}
	
	public void scan() {
		InputTabe text = new InputTabe(source.getText());
		scanner.scan(text);
		Vector<Token> tokens = scanner.getTokens();
		Vector<Error> errors = scanner.getErrors();
		
		tokenModel.setRowCount(0);
		for (Token token : tokens) {
			tokenModel.addRow(new String[] { token.getValue(), token.getType()});
		}
		
		errorModel.clear();
		if(errors.size() > 0) {
			result.setText("Nombre d'erreur Lexicales : " + Error.getErrorCounter());
			for (Error error : errors) {
				errorModel.addElement(error);
			}
		}
		else {
			result.setText("Aucun erreur Lexicale");
		}
	}
	
	public void newFile() {
		source.setText("");
	}
	
	public void saveTokens(String path) {
		if (!path.endsWith("/")) path = path + "/";
		Vector<Token> tokens = table.getTokens();
		Hashtable<String, Vector<Token>> h = new Hashtable<>();
		
		//classification des tokens
		for (Token token : tokens) {
			Vector<Token> htokens = h.get(token.getType());
			if(htokens == null) {
				htokens = new Vector<>();
				h.put(token.getType(), htokens);
			}
			htokens.add(token);
		}
		
		//netoyage di repertoire
		File folder = new File(path);
		File f[] = folder.listFiles();
		for (File file : f) {
			file.delete();
		}
		
		// Enregistrement
		for (String key : h.keySet()) {
			try {
				PrintWriter out = new PrintWriter(path + key + ".txt");
				Vector<Token> htokens = h.get(key);
				for (Token token : htokens) {
					out.println(token.getValue());
				}
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}

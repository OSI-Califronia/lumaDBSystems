package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import data.IDBHandle;

public class MainWindow extends JFrame {

	private IDBHandle dbHandler;
	
	public MainWindow(IDBHandle dbHandler) {
		super();		
		this.dbHandler = dbHandler;
		
		initialize();
	}
	
	private void initialize() {
		this.setSize(new Dimension(800, 600));
		
		
	}
	
}

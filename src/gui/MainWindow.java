package gui;

import gui.navigation.MnuMain;
import gui.tables.TblVacationList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.IDBHandle;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private class CloseAction extends WindowAdapter implements ActionListener {
		@Override
		public void windowClosing(WindowEvent e) {
		     doCloseAction();
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			doCloseAction();
		}
	
		private void doCloseAction() {
			int n = JOptionPane.showConfirmDialog(
				MainWindow.this,
				"Wollen Sie die Anwendung wirklich beenden?",
				"Exit",
				JOptionPane.YES_NO_OPTION
					);
			
			if (n == JOptionPane.YES_OPTION) {
				System.exit(1);
			}
		}
	}

	private IDBHandle dbHandler;
	protected CloseAction closeAction;
	
	protected MnuMain mnuMain;
	protected JScrollPane bpaTable;
	private TblVacationList tblVacation;
	
	public MainWindow(IDBHandle dbHandler) {
		super();		
		this.dbHandler = dbHandler;
		
		initialize();
	}
	
	private void initialize() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.setTitle("Woerterbuch");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(getCloseAction());
        Dimension dim = new Dimension(800, 600);
        this.setPreferredSize(dim);
        this.setSize(dim);
        this.pack();
        
        this.setJMenuBar(getMnuMain());
       
        this.add(getBpaTable(), BorderLayout.CENTER);
	}
	
	public MnuMain getMnuMain() {
		if (mnuMain == null) {
			mnuMain = new MnuMain(this);
		}
		return mnuMain;
	}
	
	public JScrollPane getBpaTable() {
	     if (bpaTable == null) {
	    	 bpaTable = new JScrollPane(getTblVacation());
	    	 bpaTable.setSize(new Dimension(800, 200));
	     }
	     return bpaTable;
	}
	    
    public TblVacationList getTblVacation() {
    	if (tblVacation == null) {
    		tblVacation = new TblVacationList(this, dbHandler);
    	}
    	return tblVacation;
	}
	    
	public CloseAction getCloseAction() {
		if (closeAction == null) {
			closeAction = new CloseAction();
		}
		return closeAction;
	}
}

package gui.navigation;

import gui.MainWindow;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MnuMain extends JMenuBar {

	private MainWindow mainWindow;

	private JMenu mnuFile;
	private JMenuItem mniClose;
	
	public MnuMain(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.add(getMnuFile());       
    }

    private JMenu getMnuFile() {
	     if (mnuFile == null) {
			mnuFile = new JMenu("Datei");		
			mnuFile.addSeparator();
			mnuFile.add(getMniClose());
	     }
	     return mnuFile;
    }
    
    private JMenuItem getMniClose(){
	     if (mniClose == null) {
	    	 mniClose = new JMenuItem("Beenden");
	    	 mniClose.addActionListener(mainWindow.getCloseAction());
	     }
	     return mniClose;
    }
	
}

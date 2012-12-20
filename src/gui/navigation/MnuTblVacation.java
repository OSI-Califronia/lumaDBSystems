package gui.navigation;

import gui.tables.TblVacationList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MnuTblVacation extends JMenu {

	private TblVacationList table;

//	private JMenuItem mniNew;
//	private JMenuItem mniDelete;
	private JMenuItem mniSearch;

	public MnuTblVacation(TblVacationList table) {
		this.table = table;
		this.setText("Funktionen");
		this.add(getMniSearch());
		this.addSeparator();
//		this.add(getMniNew());
//		this.add(getMniDelete());
	}

//	private JMenuItem getMniNew(){
//	     if (mniNew == null) {
//	     mniNew = new JMenuItem("Neues Wort anlegen");
//	     mniNew.addActionListener(table.getNewAction());
//	}
//	return mniNew;
//	    }
//
//	private JMenuItem getMniDelete(){
//	     if (mniDelete == null) {
//	     mniDelete = new JMenuItem("Wort Loeschen");
//	     mniDelete.addActionListener(table.getDeleteAction());
//	}
//	return mniDelete;
//	    }

	private JMenuItem getMniSearch() {
		if (mniSearch == null) {
		     mniSearch = new JMenuItem("Wort suchen");
		     mniSearch.addActionListener(table.getSearchAction());
		}
		return mniSearch;
	}
	
}

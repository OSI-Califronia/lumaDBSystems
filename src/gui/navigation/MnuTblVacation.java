package gui.navigation;

import gui.tables.TblVacationList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MnuTblVacation extends JMenu {

	private TblVacationList table;

	//	private JMenuItem mniNew;
	private JMenuItem mniBook;
	private JMenuItem mniSearch;

	public MnuTblVacation(TblVacationList table) {
		this.table = table;
		this.setText("Funktionen");
		this.add(getMniSearch());
		this.addSeparator();
		this.add(getMniBook());
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
	private JMenuItem getMniBook(){
		if (mniBook == null) {
			mniBook = new JMenuItem("book selected");
			mniBook.addActionListener(table.getBookSelectedAction());
		}
		return mniBook;
	}

	private JMenuItem getMniSearch() {
		if (mniSearch == null) {
			mniSearch = new JMenuItem("Ferienwohnung suchen");
			mniSearch.addActionListener(table.getSearchAction());
		}
		return mniSearch;
	}

}

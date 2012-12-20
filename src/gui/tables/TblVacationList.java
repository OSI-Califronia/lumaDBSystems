package gui.tables;

import gui.MainWindow;
import gui.dialogs.DlgVacationSearch;
import gui.navigation.MnuTblVacation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.IDBHandle;
import data.ReservationSearchBean;
import data.VacationBean;

public class TblVacationList extends JTable {
	
	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getSearchDialog().setVisible(true);
		}
	}
	
	private IDBHandle handle;
	private static VacationTableModel model;

	private MainWindow mainWindow;
	private MnuTblVacation tblMenu;
	private DlgVacationSearch dlgSearch;


	protected SearchAction searchAction;

	public TblVacationList(MainWindow mainWindow, IDBHandle handle) {
		super(getTableModel());

		this.mainWindow = mainWindow;
		
		this.mainWindow.getJMenuBar().add(getTblMenu());
		this.mainWindow.pack();
		this.mainWindow.repaint();
	}

	

	public void refreshList() {
		doSearch(new ReservationSearchBean());
	}

	// public void showNoEntryFoundMessage() {
	// JOptionPane.showMessageDialog(frmMainWindow,
	// "Keinen Eintrag mit den Suchkriterien gefunden");
	// }

	public MnuTblVacation getTblMenu() {
		if (tblMenu == null) {
			tblMenu = new MnuTblVacation(this);
		}
		return tblMenu;
	}

	public static VacationTableModel getTableModel() {
		if (model == null) {
			model = new VacationTableModel();
		}
		return model;
	}

	public ActionListener getSearchAction() {
		if (searchAction == null) {
			searchAction = new SearchAction();
		}
		return searchAction;
	}

	public IDBHandle getHandler() {
		return handle;
	}
	
	public DlgVacationSearch getSearchDialog() {
		if (dlgSearch == null) {
			dlgSearch = new DlgVacationSearch(this);			
		}
		return dlgSearch;
	}
	
	public void doSearch(ReservationSearchBean searchBean) {
		List<VacationBean> list = getHandler().searchForHolidayVacation(searchBean);
		
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(mainWindow,
					"Für diese Suchkriterien wurde kein Eintrag gefunden");
			return;
		}
	
		getTableModel().setDataToModel(list);			
	}
	
}

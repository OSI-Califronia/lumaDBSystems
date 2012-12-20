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
			getSearchDialog().setModal(true);
			
//			DateFormat deDate = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
//
//			
//			try {
//				ReservationSearchBean searchBean = new ReservationSearchBean(
//						"ES", 2, deDate.parse("01.11.2012"), deDate.parse("21.11.2012"), 2);
//				
//				List<VacationBean> list = getHandler().searchForHolidayVacation(searchBean);
//				getTableModel().setDataToModel(list);			
//				
//			} catch (ParseException e1) {				
//				e1.printStackTrace();
//			}
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
		
		this.handle = handle;

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
					"F�r diese Suchkriterien wurde kein Eintrag gefunden");
			return;
		}
		
		getSearchDialog().setVisible(false);	
		mainWindow.setVisible(true);
		getTableModel().setDataToModel(list);			
	}
	
}

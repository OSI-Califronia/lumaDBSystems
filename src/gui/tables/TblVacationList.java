package gui.tables;

import gui.MainWindow;
import gui.dialogs.DlgVacationBook;
import gui.dialogs.DlgVacationSearch;
import gui.navigation.MnuTblVacation;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.IDBHandle;
import data.ReservationSearchBean;
import data.VacationBean;

@SuppressWarnings("serial")
public class TblVacationList extends JTable {
	
	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getSearchDialog().setVisible(true);
			getSearchDialog().setModal(true);
		}
	}
	
	private class BookSelectedAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (resultBuffer == null) 
					return;
				VacationBean v = resultBuffer.get(TblVacationList.this.getSelectedRow());
				DlgVacationBook bookDialog = new DlgVacationBook(searchBuffer, v, TblVacationList.this);
				bookDialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
				bookDialog.setVisible(true);
			} catch (IndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(mainWindow, "Kein Eintrag selektiert!");
			}
		}
	}
	
	private IDBHandle handle;
	private static VacationTableModel model;
	List<VacationBean> resultBuffer;
	ReservationSearchBean searchBuffer = null;
	
	private MainWindow mainWindow;
	private MnuTblVacation tblMenu;
	private DlgVacationSearch dlgSearch;

	private SearchAction searchAction;
	private BookSelectedAction bookAction;

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
	
	public ActionListener getBookSelectedAction() {
		if (bookAction == null) {
			bookAction = new BookSelectedAction();
		}
		return bookAction;
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
		this.searchBuffer = searchBean;
		resultBuffer = getHandler().searchForHolidayVacation(searchBean);
		
		if (resultBuffer.isEmpty()) {
			JOptionPane.showMessageDialog(mainWindow,
					"Für diese Suchkriterien wurde kein Eintrag gefunden");
			return;
		}
		
		getSearchDialog().setVisible(false);	
		mainWindow.setVisible(true);
	
		getTableModel().setDataToModel(resultBuffer);			
	}
	
}

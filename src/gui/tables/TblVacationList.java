package gui.tables;

import gui.MainWindow;
import gui.navigation.MnuTblVacation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.IDBHandle;
import data.VacationBean;

public class TblVacationList extends JTable {
	
	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			VacationBean searchBean = new VacationBean<String, String>();			
	
			List<VacationBean> list = getHandler().searchForHolidayVacation();
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(mainWindow,
						"Für diese Suchkriterien wurde kein Eintrag gefunden");
				return;
			}
		
			getTableModel().setDataToModel(list);			
		}
	}
	
	private IDBHandle handle;
	private static VacationTableModel model;

	private MainWindow mainWindow;
	private MnuTblVacation tblMenu;

//	private BindDictionaryData binder;


	protected SearchAction searchAction;

	public TblVacationList(MainWindow mainWindow, IDBHandle handle) {
		super(getTableModel());

		this.mainWindow = mainWindow;
		
//		this.mainWindow.getJMenuBar().add(getTblMenu());
//		frmMainWindow.pack();
//		frmMainWindow.repaint();
	}

	

	public void refreshList() {
//		getBinder().refreshList();
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

//	public BindDictionaryData getBinder() {
//		if (binder == null) {
//			binder = new BindDictionaryData(this);
//		}
//		return binder;
//		return null;
//	}

	
}

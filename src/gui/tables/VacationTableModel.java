package gui.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.VacationBean;


public class VacationTableModel extends AbstractTableModel {
	
	public static final int NUMBER = 1;
	public static final int NAME = 2;
	public static final int ANZZIMMER = 3;
	public static final int GROESE = 4;
	public static final int PREIS = 5;
	public static final int ISOLAND = 6;
	public static final int LANDNAME = 7;
	public static final int ORT = 8;
	
	private List<VacationBean> data;
	private String[] columnNames;
	
	public void setDataToModel(List<VacationBean> data) {
		this.data = data;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}

	public String getColumnName(int col) {
		return getColumnNames()[col].toString();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		VacationBean dataBean = data.get(rowIndex);

		switch (columnIndex) {
		case NUMBER:
			return dataBean.getNr();
		case NAME:
			return dataBean.getName();
		case ANZZIMMER:
			return dataBean.getAnzZimmer();
		case GROESE:
			return dataBean.getGroesse();
		case PREIS:
			return dataBean.getPreis();
		case ISOLAND:
			return dataBean.getLand().getISOLand();
		case LANDNAME:
			return dataBean.getLand().getName();
		case ORT:
			return dataBean.getOrt();
		default:
			return "";
		}
	}

	public VacationBean getValueAtRowIndex(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < data.size()) {
			return data.get(rowIndex);
		}
		return data.get(0);
	}

	public String[] getColumnNames() {
		if (columnNames == null) {
			columnNames = new String[] {"FerienwohnungNr", "Name", "AnzZimmer", "Groesse", "Preis" , "ISOLand", "Land", "Ort"};
		}
		return columnNames;
	}


}

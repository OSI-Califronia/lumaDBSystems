package main;

import gui.MainWindow;
import data.DBHandler;
import data.IDBHandle;

public class Main {

	
	public static void main(String[] args) {
		IDBHandle handle = new DBHandler();
		
//		handle.searchForHolidayVacation();
		
		MainWindow mainWindow = new MainWindow(handle);
		mainWindow.setVisible(true);
	}
}

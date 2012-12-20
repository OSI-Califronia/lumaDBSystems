package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;

public class DBHandler implements IDBHandle {	
	
	public final static String DB_URL = "jdbc:oracle:thin:@oracle11g.in.htwg-konstanz.de:1521:ora11g";

	private Connection conn;
	
	
	public DBHandler() {
		super();
	}
	
	private void setConnection() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			DriverManager.registerDriver(new OracleDriver());			
			conn =  DriverManager.getConnection(DB_URL, "dbsys11", "dbsys11");
			conn.setAutoCommit(false);
		}
		
	}
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		setConnection();
		return conn;
	}
	
	private void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	public List<VacationBean> searchForHolidayVacation(ReservationSearchBean searchBean) {
		List<VacationBean> retVal = new LinkedList<VacationBean>();
		
		try {
			
			setConnection();
		
			Statement stmt = getConnection().createStatement();
			
			ResultSet res = stmt.executeQuery("SELECT FerienwohnungNr, Name, AnzZimmer, Groesse, Preis , ISOLand, ort FROM Ferienwohnung");
						
			while (res.next()) {
				VacationBean bean = new VacationBean();
				bean.setNr(res.getInt(1));
				bean.setName(res.getString(2));
				bean.setAnzZimmer(res.getInt(3));
				bean.setGroesse(res.getInt(4));
				bean.setPreis(res.getDouble(5));
				// TODO: bean.setiSOLand(res.getString(6));
				bean.setOrt(res.getString(7));			
				retVal.add(bean);
				
//				System.out.println(bean);
			}
		
		} catch (SQLException esql) {		
			esql.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return retVal;
	}

	@Override
	public List<CountryBean> getAllCountries() {
		List<CountryBean> retVal = new LinkedList<CountryBean>();
		try {
			setConnection();
			Statement stmt = getConnection().createStatement();
			ResultSet res = stmt.executeQuery("SELECT ISO, Name FROM Land");
			while (res.next()) {
				CountryBean bean = new CountryBean();
				bean.setISOLand(res.getString(1));
				bean.setName(res.getString(2));
				retVal.add(bean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return retVal;
	}

	@Override
	public List<EquipmentBean> getAllEquipments() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

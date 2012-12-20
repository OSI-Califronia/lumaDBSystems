package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import oracle.jdbc.driver.OracleDriver;

public class DBHandler implements IDBHandle {	

	public final static String DB_URL = "jdbc:oracle:thin:@oracle11g.in.htwg-konstanz.de:1521:ora11g";

	private Connection conn;


	public DBHandler() {
		super();
	}

	private void setConnection() throws SQLException, ClassNotFoundException {
		if (conn == null || conn.isClosed()) {
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


	public static void main(String[] args) {
		try {
			DBHandler handler = new DBHandler();
			DateFormat deDate = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);

			ReservationSearchBean searchBean;

			searchBean = new ReservationSearchBean(
					"ES", 2, deDate.parse("01.11.2012"), deDate.parse("21.11.2012"), 2);


			System.out.println("######### häuser in ES mit sauna (nr. 2) zwischen 1.11.2012 und 21.11.2012 ######");
			
			List<VacationBean> list = handler.searchForHolidayVacation(searchBean);
			for (VacationBean vb : list) {
				System.out.println(vb.toString());
			}
			
			System.out.println("\n\n ######### Alle Länder ###### ");
			
			List<CountryBean> countries = handler.getAllCountries();
			for (CountryBean cb : countries) {
				System.out.println(cb.toString());
			}
			
			System.out.println("\n\n ######### Alle Ausstattungen ###### ");
			
			List<EquipmentBean> austattungen = handler.getAllEquipments();
			for (EquipmentBean eb : austattungen) {
				System.out.println(eb.toString());
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<VacationBean> searchForHolidayVacation(ReservationSearchBean searchBean) {
		List<VacationBean> retVal = new LinkedList<VacationBean>();

		try {
			String searchSql = "SELECT w.FerienwohnungNr, w.Name, w.AnzZimmer, w.Groesse, w.Preis, w.ort, l.ISO, l.Name" + 
					" FROM Ferienwohnung w" + 
					" INNER JOIN Land l ON l.ISO = w.ISOLand" + 
					" INNER JOIN AustFeZuordnung z ON z.FeNr = w.FerienwohnungNr" + 
					" INNER JOIN Ausstattung a ON a.AustNr = z.AustNr" + 
					" WHERE a.AustNr = ?" + 
					"  AND w.ISOLAnd = ?" + 
					"  AND NOT EXISTS (" + 
					"    SELECT r.BuchungsNr" + 
					"    FROM Reservierung r" + 
					"    WHERE r.FeNr = w.FerienwohnungNr " + 
					"          AND w.anzZimmer >= ?" + 
					"          AND" + 
					"          ( r.DatumVon BETWEEN ? AND ?" + 
					"            OR  " + 
					"            r.DatumBis BETWEEN ? AND ?" + 
					"            OR" + 
					"            (" + 
					"              r.DatumVon < ?" + 
					"              AND" + 
					"              r.DatumBis > ?" + 
					"            )" + 
					"          )" + 
					"  )";

			setConnection();

			PreparedStatement query = getConnection().prepareStatement(searchSql);
			query.setInt(1, searchBean.getAustNr());  // equipment id
			query.setString(2, searchBean.getIsoLand());
			query.setInt(3, searchBean.getMinAnzahlZimmer());
			query.setDate(4, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(5, new java.sql.Date(searchBean.getDatumBis().getTime()));
			query.setDate(6, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(7, new java.sql.Date(searchBean.getDatumBis().getTime()));
			query.setDate(8, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(9, new java.sql.Date(searchBean.getDatumBis().getTime()));

			ResultSet res = query.executeQuery();

			while (res.next()) {
				VacationBean bean = new VacationBean();
				bean.setNr(res.getInt(1));
				bean.setName(res.getString(2));
				bean.setAnzZimmer(res.getInt(3));
				bean.setGroesse(res.getInt(4));
				bean.setPreis(res.getDouble(5));
				bean.setOrt(res.getString(6));	
				bean.setLand(new CountryBean(res.getString(7), res.getString(8)));
				retVal.add(bean);
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
		List<EquipmentBean> retVal = new LinkedList<EquipmentBean>();
		try {
			setConnection();
			Statement stmt = getConnection().createStatement();
			ResultSet res = stmt.executeQuery("SELECT AustNr, Name FROM Ausstattung");
			while (res.next()) {
				EquipmentBean bean = new EquipmentBean();
				bean.setEquipNr(res.getInt(1));
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


}

package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
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
					"ES", 2, deDate.parse("01.11.2012"), deDate.parse("21.11.2012"), -1);


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
		String searchSql = "";
		try {
			searchSql = "SELECT DISTINCT w.FerienwohnungNr, w.Name, w.AnzZimmer, w.Groesse, w.Preis, w.ort, l.ISO, l.Name\n" + 
					" FROM Ferienwohnung w\n" + 
					" INNER JOIN Land l ON l.ISO = w.ISOLand\n" + 
					" INNER JOIN AustFeZuordnung z ON z.FeNr = w.FerienwohnungNr\n" + 
					" INNER JOIN Ausstattung a ON a.AustNr = z.AustNr\n" + 
					" WHERE w.ISOLAnd = ?";
			if (searchBean.getAustNr() >= 0) {  // ausstattung optional
				searchSql += "  AND a.AustNr = ?\n";
			}
			searchSql += " AND NOT EXISTS ( \n" + 
					"      SELECT r.BuchungsNr \n" + 
					"      FROM Reservierung r \n" + 
					"      WHERE r.FeNr = w.FerienwohnungNr \n" + 
					"          AND w.anzZimmer >= ? \n" + 
					"          AND \n" + 
					"          ( r.DatumVon BETWEEN ? AND ? \n" + 
					"            OR  " + 
					"            r.DatumBis BETWEEN ? AND ? \n" + 
					"            OR \n" + 
					"            ( \n" + 
					"              r.DatumVon < ? \n" + 
					"              AND \n" + 
					"              r.DatumBis > ? \n" + 
					"            ) \n" + 
					"          ) \n" + 
					"  ) \n";

			setConnection();

			PreparedStatement query = getConnection().prepareStatement(searchSql);
			int i = 1;
			query.setString(i++, searchBean.getIsoLand());
			if (searchBean.getAustNr() >= 0) {
				query.setInt(i++, searchBean.getAustNr());  // equipment id
			}
			query.setInt(i++, searchBean.getMinAnzahlZimmer());
			query.setDate(i++, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(i++, new java.sql.Date(searchBean.getDatumBis().getTime()));
			query.setDate(i++, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(i++, new java.sql.Date(searchBean.getDatumBis().getTime()));
			query.setDate(i++, new java.sql.Date(searchBean.getDatumVon().getTime()));
			query.setDate(i++, new java.sql.Date(searchBean.getDatumBis().getTime()));

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
			System.out.println(searchSql);
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
		retVal.add(new EquipmentBean(-1, "(nichts)"));
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

	@Override
	public boolean createReservation(ReservationSearchBean reserv, VacationBean vac, int kundenNr) {
		DateFormat deDate = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
		Connection c = null;
		try {
			setConnection();
			c = getConnection();
			Statement stmt = c.createStatement();
			String sql = 
			  "INSERT INTO Reservierung (" +
					" DatumVon, " +
					" DatumBis, " +
					" FeNr, " +
					" KundenNr, " +
					" BuchungsNr, ErstellungsDatum, Zahlungseingang, Bewertung)" +
					" VALUES (" +
					" to_date('" +  deDate.format(reserv.getDatumVon()) + "', 'dd.mm.yyyy')," +
					" to_date('" + deDate.format(reserv.getDatumBis()) + "', 'dd.mm.yyyy'), " +
					vac.getNr() + ", " +
					kundenNr + ", " +
			" NULL, NULL, NULL, NULL)";
			
			stmt.executeUpdate(sql);
					
			System.out.println(sql);
			c.commit();
			return true;
		} catch (SQLException e) {
			try {
				if (c != null && !c.isClosed()) {
					c.rollback();
				}
			} catch (SQLException e1) {
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return false;
	}

}

package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.CountryBean;
import data.ReservationSearchBean;
import data.VacationBean;

@SuppressWarnings("serial")
public class DlgVacationBook extends JFrame {
	
	private final ReservationSearchBean reservation;
	private final VacationBean vacation;
	private static final DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
	
	public DlgVacationBook(ReservationSearchBean reserv, VacationBean vac) {
		super();
		this.reservation = reserv;
		this.vacation = vac;
		
		initializeComponents();
	}

	public static void main(String[] args) throws ParseException {
		ReservationSearchBean r = new ReservationSearchBean(
				"DE", 2, df.parse("10.12.2012"), df.parse("02.01.2013"), 7);
		VacationBean v = new VacationBean(6, "Bruchbude Nr. 5", 2, 50, 80.0, new CountryBean("DE", "Deutschland"), "Z' Schaffhuuse");
		DlgVacationBook vb = new DlgVacationBook(r,v);
		vb.setVisible(true);

	}

	private void initializeComponents() {
		this.setTitle("Ferienwohnung buchen");
		this.setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel rootPane = new JPanel();
		rootPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Zusammenfassung"));
		rootPane.setLayout(new GridLayout(8, 2, 10, 10));
		
		rootPane.add(new JLabel("Name:"));
		rootPane.add(new JLabel(this.vacation.getName()));
		
		rootPane.add(new JLabel("Von:"));
		rootPane.add(new JLabel(df.format(this.reservation.getDatumVon())));		
		
		rootPane.add(new JLabel("Bis"));
		rootPane.add(new JLabel(df.format(this.reservation.getDatumBis())));
		
		rootPane.add(new JLabel("Zimmeranzahl:"));
		rootPane.add(new JLabel(String.valueOf(this.vacation.getAnzZimmer())));
		
		rootPane.add(new JLabel("Groesse:"));
		rootPane.add(new JLabel(String.valueOf(this.vacation.getGroesse()) + " qm"));
		
		rootPane.add(new JLabel("Tagespreis (€):"));
		rootPane.add(new JLabel(String.valueOf(this.vacation.getPreis())));
		
		this.add(rootPane, BorderLayout.NORTH);
	}
	
}

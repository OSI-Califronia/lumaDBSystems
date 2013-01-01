package gui.dialogs;

import gui.tables.TblVacationList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.ReservationSearchBean;
import data.VacationBean;

@SuppressWarnings("serial")
public class DlgVacationBook extends JDialog {
	
	private final ReservationSearchBean reservation;
	private final VacationBean vacation;
	private static final DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
	private final TblVacationList table;
	
	private JTextField txtKundenNr;
	
	public DlgVacationBook(ReservationSearchBean reserv, VacationBean vac, TblVacationList table) {
		super();
		this.reservation = reserv;
		this.vacation = vac;
		this.table = table;
		
		initializeComponents();
	}

//	public static void main(String[] args) throws ParseException {
//		ReservationSearchBean r = new ReservationSearchBean(
//				"DE", 2, df.parse("10.12.2012"), df.parse("02.01.2013"), 7);
//		VacationBean v = new VacationBean(6, "Bruchbude Nr. 5", 2, 50, 80.0, new CountryBean("DE", "Deutschland"), "Z' Schaffhuuse");
////		DlgVacationBook vb = new DlgVacationBook(r,v);
////		vb.setVisible(true);
//
//	}

	private void initializeComponents() {
		this.setTitle("Ferienwohnung buchen");
		this.setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Zusammenfassung"));
		infoPanel.setLayout(new GridLayout(8, 2, 10, 10));
		
		infoPanel.add(new JLabel("Name:"));
		infoPanel.add(new JLabel(this.vacation.getName()));
		
		infoPanel.add(new JLabel("Von:"));
		infoPanel.add(new JLabel(df.format(this.reservation.getDatumVon())));		
		
		infoPanel.add(new JLabel("Bis"));
		infoPanel.add(new JLabel(df.format(this.reservation.getDatumBis())));
		
		infoPanel.add(new JLabel("Zimmeranzahl:"));
		infoPanel.add(new JLabel(String.valueOf(this.vacation.getAnzZimmer())));
		
		infoPanel.add(new JLabel("Groesse:"));
		infoPanel.add(new JLabel(String.valueOf(this.vacation.getGroesse()) + " qm"));
		
		infoPanel.add(new JLabel("Tagespreis (€):"));
		infoPanel.add(new JLabel(String.valueOf(this.vacation.getPreis())));
		
		txtKundenNr = new JTextField(12);
		infoPanel.add(new JLabel("Kunden-Nr.:"));
		infoPanel.add(txtKundenNr);
		
		 JButton btnClose = new JButton("Abbrechen");
		 btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgVacationBook.this.setVisible(false);
			}
		});
		infoPanel.add(btnClose);
		
		 JButton btnBook = new JButton("Buchen");
		 btnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getHandler().createReservation(reservation, vacation, Integer.valueOf(txtKundenNr.getText()))) {
					JOptionPane.showMessageDialog(DlgVacationBook.this, "Buchung wurde akzeptiert.");
				} else {
					JOptionPane.showMessageDialog(DlgVacationBook.this, "Fehler bei der Buchung!");
				}
				DlgVacationBook.this.setVisible(false);
			}
		});
		infoPanel.add(btnBook);
		
		
		this.add(infoPanel, BorderLayout.NORTH);
	}
	
}

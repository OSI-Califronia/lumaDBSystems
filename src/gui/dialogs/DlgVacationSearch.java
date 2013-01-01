package gui.dialogs;

import gui.tables.TblVacationList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import data.CountryBean;
import data.EquipmentBean;
import data.ReservationSearchBean;

@SuppressWarnings("serial")
public class DlgVacationSearch extends JDialog {

	private JPanel bpaButtons;
	private JButton btnOk;
	private JButton btnCancel;
	
	private JPanel bpaDefault;
	private JTextField tfiAnzZimmer;	
	private JCalendar calFrom;	
	private JCalendar calTill;	
	private JComboBox<CountryBean> cbxCountry;
	private JComboBox<EquipmentBean> cbxEquipment;
	private Insets defaultInsets;
	
	private TblVacationList table;
	
	public DlgVacationSearch(TblVacationList table) {
		super();
		this.table = table;
		
		initialize();
	}
	
	private void initialize() {
		Dimension size = new Dimension(500, 500);
		this.setSize(size);
		this.setPreferredSize(size);
		//this.setBorder(BorderFactory.createTitledBorder("Ferienwohnung Suchen"));
		this.setLayout(new BorderLayout());
		this.add(getBpaDefault(), BorderLayout.CENTER);
		this.add(getBpaButtons(), BorderLayout.SOUTH);		
//		this.getRootPane().setBorder(BorderFactory.createTitledBorder("Ferienwohnung Suchen"));
//		this.getRootPane().setLayout(new BorderLayout());
//		this.getRootPane().add(getBpaDefault(), BorderLayout.CENTER);
//		this.getRootPane().add(getBpaButtons(), BorderLayout.SOUTH);		
	}
	
	private Insets getDefaultInsets() {
		if (defaultInsets == null) {
			defaultInsets = new Insets(5, 5, 5, 5);
			
		}
		return defaultInsets;
	}
	
	protected JPanel getBpaDefault() {
		if (bpaDefault == null) {
			bpaDefault = new JPanel();
			bpaDefault.setLayout(new GridBagLayout());
			
			// DatumVon
			GridBagConstraints c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 0;
			c.gridy = 0;
			bpaDefault.add(new JLabel("Datum von:"), c);
			
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 0;
			c.gridy = 1;
			bpaDefault.add(getCalFrom(), c);
			
			// DatumBis
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 1;
			c.gridy = 0;
			bpaDefault.add(new JLabel("Datum bis:"), c);
			
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 1;
			c.gridy = 1;
			bpaDefault.add(getCalTill(), c);
			
			// AnzZimmer
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 0;
			c.gridy = 2;
			bpaDefault.add(new JLabel("Anzahl Zimmer:"), c);
			
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 1;
			c.gridy = 2;
			bpaDefault.add(getTfiAnzZimmer(), c);
			
			// Land
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 0;
			c.gridy = 3;
			bpaDefault.add(new JLabel("Land:"), c);
			
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 1;
			c.gridy = 3;
			bpaDefault.add(getCbxCountry(), c);
			
			// Ausstattung
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 0;
			c.gridy = 4;
			bpaDefault.add(new JLabel("Ausstattung:"), c);
			
			c = new GridBagConstraints();
			c.insets = getDefaultInsets();
			c.gridx = 1;
			c.gridy = 4;
			bpaDefault.add(getCbxEquipment(), c);	
			
		}
		return bpaDefault;
	}

	public JTextField getTfiAnzZimmer() {
		if (tfiAnzZimmer == null) {
			tfiAnzZimmer = new JTextField(2);
			tfiAnzZimmer.setPreferredSize(new Dimension(40, 20));
		}
		return tfiAnzZimmer;
	}
	
	public JCalendar getCalFrom() {
		if (calFrom == null) {
			calFrom = new JCalendar();
			calFrom.setPreferredSize(new Dimension(200, 200));
		}
		return calFrom;		
	}


	public JCalendar getCalTill() {
		if (calTill == null) {
			calTill = new JCalendar();
			calTill.setPreferredSize(new Dimension(200, 200));
		}
		return calTill;		
	}

	
	
	public JComboBox<EquipmentBean> getCbxEquipment() {
		if (cbxEquipment == null) {
			cbxEquipment = new JComboBox<EquipmentBean>(table.getHandler().getAllEquipments().toArray(new EquipmentBean[0]));
			cbxEquipment.setPreferredSize(new Dimension(200, 30));
		}
		return cbxEquipment;		
	}
	
	public JComboBox<CountryBean> getCbxCountry() {
		if (cbxCountry == null) {
			cbxCountry = new JComboBox<CountryBean>(table.getHandler().getAllCountries().toArray(new CountryBean[0]));			
			cbxCountry.setPreferredSize(new Dimension(200, 30));
		}
		return cbxCountry;		
	}


	protected JPanel getBpaButtons() {
		if (bpaButtons == null) {
			bpaButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bpaButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			bpaButtons.setPreferredSize(new Dimension(500, 30));
			bpaButtons.add(getBtnOk());
			bpaButtons.add(getBtnCancel());		
		}
		return bpaButtons;
	}


	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setText("Ok");
			btnOk.setPreferredSize(new Dimension(120, 20));
			btnOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					ReservationSearchBean searchBean = new ReservationSearchBean();
					
					if (!getTfiAnzZimmer().getText().trim().equals("")) {
						searchBean.setAustNr(Integer.valueOf(getTfiAnzZimmer().getText().trim()));
					}
					searchBean.setDatumBis(getCalTill().getDate());
					searchBean.setDatumVon(getCalFrom().getDate());
					
					if (getCbxCountry().getSelectedIndex() != -1) {
						searchBean.setIsoLand((((CountryBean) getCbxCountry().getSelectedItem()).getISOLand()));
					}
					
					if (cbxEquipment.getSelectedItem() instanceof EquipmentBean) {
						searchBean.setAustNr(((EquipmentBean) cbxEquipment.getSelectedItem()).getEquipNr());
					}
					
					table.doSearch(searchBean);					
				}
			});
		}
		return btnOk;
	}

	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setText("zurück");
			btnCancel.setPreferredSize(new Dimension(120, 20));
			btnCancel.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					DlgVacationSearch.this.setVisible(false);
				}
			});
		}
	
		return btnCancel;
	}


	
	
	
}

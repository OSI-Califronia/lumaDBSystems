package data;

import java.util.Date;

public class ReservationSearchBean {

	private String isoLand;
	private int minAnzahlZimmer;
	private Date datumVon;
	private Date datumBis;
	private int AustNr;
	
	public ReservationSearchBean() {
		super();
	}
	
	public ReservationSearchBean(String isoLand, int minAnzahlZimmer,
			Date datumVon, Date datumBis, int austNr) {
		super();
		this.isoLand = isoLand;
		this.minAnzahlZimmer = minAnzahlZimmer;
		this.datumVon = datumVon;
		this.datumBis = datumBis;
		AustNr = austNr;
	}
	public String getIsoLand() {
		return isoLand;
	}
	public void setIsoLand(String isoLand) {
		this.isoLand = isoLand;
	}
	public int getMinAnzahlZimmer() {
		return minAnzahlZimmer;
	}
	public void setMinAnzahlZimmer(int minAnzahlZimmer) {
		this.minAnzahlZimmer = minAnzahlZimmer;
	}
	public Date getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}
	public Date getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(Date datumBis) {
		this.datumBis = datumBis;
	}
	public int getAustNr() {
		return AustNr;
	}
	public void setAustNr(int austNr) {
		AustNr = austNr;
	}
	
}

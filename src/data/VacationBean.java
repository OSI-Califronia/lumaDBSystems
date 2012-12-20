package data;

public class VacationBean {

	private Integer nr;
	private String name;
	private Integer anzZimmer;
	private Integer groesse;
	private Double  preis;
	private CountryBean land;
	private String  ort;
	
	public VacationBean() {
		super();
	}

	public VacationBean(Integer nr, String name, Integer anzZimmer,
			Integer groesse, Double preis, CountryBean land, String ort) {
		super();
		this.nr = nr;
		this.name = name;
		this.anzZimmer = anzZimmer;
		this.groesse = groesse;
		this.preis = preis;
		this.land = land;
		this.ort = ort;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnzZimmer() {
		return anzZimmer;
	}

	public void setAnzZimmer(Integer anzZimmer) {
		this.anzZimmer = anzZimmer;
	}

	public Integer getGroesse() {
		return groesse;
	}

	public void setGroesse(Integer groesse) {
		this.groesse = groesse;
	}

	public Double getPreis() {
		return preis;
	}

	public void setPreis(Double preis) {
		this.preis = preis;
	}

	public CountryBean getLand() {
		return land;
	}

	public void setLand(CountryBean land) {
		this.land = land;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getNr());
		sb.append(" ");
		sb.append(getName());
		sb.append(" ");
		sb.append(getAnzZimmer());
		sb.append(" ");
		sb.append(getGroesse());
		sb.append(" ");
		sb.append(getOrt());
		sb.append(" ");
		sb.append(getPreis());
		return sb.toString();
	}
	
}

package data;

public class CountryBean {
	
	private String ISOLand;
	private String Name;
	
	public String getISOLand() {
		return ISOLand;
	}
	
	public void setISOLand(String isoLand) {
		ISOLand = isoLand;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	@Override
	public String toString() {
		return this.getName() + " (" + this.getISOLand() + ")";
	}
	
	public CountryBean() {super();}
	
	public CountryBean(String iso, String name) {
		this.setISOLand(iso);
		this.setName(name);
	}

}

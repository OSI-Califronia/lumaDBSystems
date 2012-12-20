package data;

public class CountryBean {
	
	private String ISOLand;
	private String Name;
	
	public String getISOLand() {
		return ISOLand;
	}
	
	public void setISOLand(String iSOLand) {
		ISOLand = iSOLand;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	@Override
	public String toString() {
		return this.getISOLand() + " " + this.getName();
	}

}

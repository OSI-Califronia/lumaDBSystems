package data;

public class EquipmentBean {
	
	private int equipNr;
	private String name;
	
	
	
	public int getEquipNr() {
		return equipNr;
	}
	public void setEquipNr(int equipNr) {
		this.equipNr = equipNr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getName() + " " + this.getEquipNr() + ")";
	}

}

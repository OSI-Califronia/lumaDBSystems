package data;

public class EquipmentBean {
	
	public EquipmentBean() {
		super();
	}
	
	public EquipmentBean(int equipNr, String name) {
		super();
		this.equipNr = equipNr;
		this.name = name;
	}

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
		return this.getName();
	}

}

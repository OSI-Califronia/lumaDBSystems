package data;

import java.util.List;

public interface IDBHandle {

	
	public List<VacationBean> searchForHolidayVacation();
	
	public List<CountryBean> getAllCountries();
	
	public List<EquipmentBean> getAllEquipments();
	
}
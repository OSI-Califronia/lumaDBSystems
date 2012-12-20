package data;

import java.util.Date;
import java.util.List;

public interface IDBHandle {

	
	public List<VacationBean> searchForHolidayVacation(ReservationSearchBean searchBean);		
	
	public List<CountryBean> getAllCountries();
	
	public List<EquipmentBean> getAllEquipments();
	
}

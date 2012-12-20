package data;

import java.util.Date;
import java.util.List;

public interface IDBHandle {

	
	public List<VacationBean> searchForHolidayVacation(
			String isoLand,
			int minAnzahlZimmer,
			Date datumVon,
			Date datumBis,
			int AustNr);
	
	public List<CountryBean> getAllCountries();
	
	public List<EquipmentBean> getAllEquipments();
	
}

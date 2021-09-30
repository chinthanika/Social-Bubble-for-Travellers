import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CountryRank {
	String [] countries;	//Array of countries in order, from worst to best (ranked by COVID cases)
	int [] ranks;	//Array of ankings of the countries in order of array countries

	public CountryRank() {		//Constructor to initialize private variables
		countries = new String[] {"usa", "india", "brazil","russia", "uk", "france", "spain", "italy", "turkey", "germany", "saudi arabia", "ireland", "denmark", "greece", "iran", "myanmar", "algeria", "kenya", "south korea", "china", "maldives", "hong kong", "somalia", "vietnam", "new zealand"};
		ranks = new int[] {5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2,2 ,2 , 2, 1, 1, 1, 1, 1};
	}
	
	public int findCountry(String country) {	//Method to loop through array countries and look for the passenger's country
		
		//Loop through array countries
		for (int i = 0; i < 25; i++) {
			if (countries[i].equals(country)) {
				return ranks[i];	//If passenger's country is found, return its rank
			}
		}
		return 0;	//Otherwise return zero
	}
	
	public void quarantinePeriod(IncomingPassenger passenger, int rank) {	//Method to decide the passenger's quarantine period, based on their country's rank
		int quarantineDays;		//Number of days they must be quarantined
		switch (rank) {		//Switch statement to go assign the quarantineDays based on rank
		case 1:
			quarantineDays = 28;
			break;
		
		case 2:
			quarantineDays = 21;
			break;
		
		case 3:
			quarantineDays = 14;
			break;
		
		case 4:
			quarantineDays = 7;
			break;
		
		case 5:
			quarantineDays = 7;
			break;
		
		default:		//Default case (eg: when rank zero, or the country is not found)
			quarantineDays = 14;
		}
		
		//Find the date the passenger can exit quarantine, using their departureDate
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");		//Create an object of type dateFormat
		Calendar calendar = Calendar.getInstance();		//Create an input of type Calendar
		calendar.setTime(passenger.getDepartureDate());
		calendar.add(Calendar.DATE, quarantineDays);	//Add the assigned quarantineDays to the departure date
		String endingDate = date.format(calendar.getTime());	//Date on which their quarantine ends
		
		//Display their information with the quarantine period.
		System.out.println("\nPassenger: " + passenger.getFullName() + "\nFrom: " + passenger.getCountry() + "\nDeparted On: " + passenger.getDepartureDate() + "\nQuarantine Period: " + quarantineDays + " days (until " + endingDate + ")");
	}
}


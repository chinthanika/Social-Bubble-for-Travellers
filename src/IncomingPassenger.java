import java.util.Date;

public class IncomingPassenger {		//A JavaBean to hold the passenger's data
	
	private String fullName;	//Passenger's full name
	private String lastName;	//Passenger's last name
	private String country;		//Passenger's country of origin
	private Date departureDate;	//Passenger's date of departure from country
	
	public IncomingPassenger() {	//Default constructor with no parameters (zero-argument constructor) 
		
	}
	
	public void setFullName(String name) {		//Mutator method for fullName
		this.fullName = name;
	}
	
	public void setlastName(String lastName) {	//Mutator method for lastName
		this.lastName = lastName;
	}
	
	public void setDepartureDate(Date date) {	//Mutator method for country
		this.departureDate = date;
	}
	
	public void setCountry(String country) {	//Mutator method for departureDate
		this.country = country;
	}
	
	public String getFullName() {	//Accessor method for fullName
		return this.fullName;
	}
	
	public String getLastName() {	//Accessor method for lastName
		return this.lastName;
	}
	
	public String getCountry() {	//Accessor method for country
		return this.country;
	}
	
	public Date getDepartureDate() {	//Accessor method for departureDate
		return this.departureDate;
	}
}

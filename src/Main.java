import java.util.Scanner;	//To accept user input
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		// The main method for the entire project
		Scanner acceptInput = new Scanner(System.in);
		boolean newPassenger = true;	//Flag to check whether there is another passenger
		
		boolean validChoice = false;	//Flag to check whether the user has made a valid choice (Used for all yes/no inputs in the main function)
		
		IncomingPassenger passenger = new IncomingPassenger();	//Create an object of type IncomingPassenger
		
		while (newPassenger) {		//Loop while there is another passenger to process 
			boolean notFullName = true;	//Flag to check whether it is a full name
			String fullName = null;
		
			//Input the passenger's full name.
			while (notFullName == true) { 	//Loop as long as it is not a full name.
				System.out.print("Full Name: ");
				fullName = acceptInput.nextLine();
		
				if (fullName.contains(" ") && !fullName.equals(" ")) {	//Check that there is an input and it is more than one name
					notFullName = false;
				}
				else {
					System.out.println("\nPlease Enter Your Full Name.");	//If not, ask user to try again
				}
	
			}
			
			passenger.setFullName(fullName);	//Use the relevant mutator method in IncomingPassenger to set the full name.
			
			//Split fullName from the end to get the last name
			int position = fullName.lastIndexOf(" ");	//Find the position of the last space
			String lastName = fullName.substring(position + 1);	//Split to find the last name
			
			passenger.setlastName(lastName);	//Use the relevant mutator method in IncomingPassenger to set the last name.
		
			String country = " ";
			
			//Input the passenger's country of origin
			while (!validChoice) {	//Loop to ensure field is not left empty
				System.out.print("\nCountry of Origin: ");	//Input the country
				country = acceptInput.nextLine();
				
				if (country.length() <= 1) {	//Checks whether there is a valid input
					System.out.print("\nPlease enter a valid country.");	//If not, ask user to try again
				}
				else {
					validChoice = true;
				}
			}
			validChoice = false;	//Changes validChoice back to false in preparation for the next use
		
			passenger.setCountry(country);	//Use the relevant mutator method in IncomingPassenger to set the country.
			
			boolean validDate = false;	//Flag to check whether a valid date has been input
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");	//Create an object of type dateFormat
			Date departureDate = null;
			
			//Input the passenger's date of departure from their country
			while (!validDate) {	//Loop to ensure that a valid date is entered
				System.out.print("\nDate of Departure from Your Country (eg: 01-Jan-2021): ");
				String convertToDate = acceptInput.nextLine();	//Input the date they left their country
			
				//Try-Catch block to input the date using dateFormat
			
				try {
					departureDate = dateFormat.parse(convertToDate);	//Convert string to date
					validDate = true;	//If successful, change validDate to true to terminate loop
					
				}catch (ParseException ex) {
					System.out.print("\nPlease enter a valid date, such as 01-Jan-2021.");	//If failed, ask user to try again
				
				}
			}
		
			passenger.setDepartureDate(departureDate);	//Use the relevant mutator method in IncomingPassenger to set the departure date.
			
			//Find the user's quarantine period
			CountryRank rankedCountries = new CountryRank();	//Create an object of type CountryRank
			int rank = rankedCountries.findCountry(passenger.getCountry().toLowerCase());	//Find the rank of the passenger's country of origin
			rankedCountries.quarantinePeriod(passenger, rank);	//Get the passenger's quarantine period
		
			String wantToVisit = " ";
			//Check whether the user intends to visit locations outside of their place of residence
			while (!validChoice) {	//Loop until a valid input (yes/no) has been entered
				System.out.print("\nWill you be visiting other locations? Yes/No: ");
				wantToVisit = acceptInput.nextLine();
				wantToVisit = wantToVisit.toLowerCase();
				
				if (!wantToVisit.equals("yes") && !wantToVisit.equals("no")) {	//Check whether the input is valid, i.e. either yes or no
					System.out.println("\nPlease respond with 'Yes' or 'No'.");		//If not, ask user to try again
				}
				else {
					validChoice = true;		//If so allow loop to terminate
				}
			}
			validChoice = false;	//Changes validChoice back to false in preparation for the next use
			
			//Check whether the passenger has permission to go where they want to go
			while(wantToVisit.contains("yes")) {
				PermissionToVisit placeToGo = new PermissionToVisit();	//Create an object of type PermissionToVist
				placeToGo.Permission(passenger.getCountry().toLowerCase(), acceptInput); 	//Call method permission from PermissionToVisit to determine whether the user has permission to travel to a location.
				
				//Loop to check whether the user has entered a valid input
				while (!validChoice) {	//Loop until a valid input (yes/no) has been entered
					System.out.print("\nWould you like to visit another location? Yes/No: ");
					String newLocation = acceptInput.nextLine();	//Input the user's choice of yes/no
					wantToVisit = newLocation.toLowerCase();	//Change input to lower case for ease of comparison
					
					if (!wantToVisit.equals("yes") && !wantToVisit.equals("no")) {	//Check whether the input is valid, i.e. either yes or no
						System.out.println("\nPlease respond with 'Yes' or 'No'.");		//If not, ask user to try again
					}
					else {
						validChoice = true;		//If not, ask user to try again
					}
				}
				validChoice = false;	//Changes validChoice back to false in preparation for the next use
			}
		
			//Check whether there is a new passenger to process
			while (!validChoice) {		//Loop until a valid input (yes/no) has been entered
				System.out.print("\nWould you like to enter data for a new passenger? Yes/No: ");
				String newUser = acceptInput.nextLine();
				newUser = newUser.toLowerCase();
				if (newUser.equals("yes") || newUser.equals("no")) {	//Check whether the input is valid, i.e. either yes or no
					validChoice = true;		//If there is a new user loop again
					
					if (newUser.equals("no")) {	
						newPassenger = false;	//If there is no new passenger, terminate the loop
					}
				}
				else {
					System.out.println("\nPease respond with 'Yes' or'No'.");	//If input is not valid, ask user to try again
				}
			}
			validChoice = false;	//Changes validChoice back to false in preparation for the next use
		}
		
		System.out.println("\nThank you for your patronage!");
		
		acceptInput.close();	//Close the Scanner object.
	}

}

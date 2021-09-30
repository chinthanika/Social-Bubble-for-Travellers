import java.util.Scanner;	

public class PermissionToVisit extends CountryRank{	//Derived class, inheriting from countryRank
	String [] placesToVisit;	//Array of types of places the passenger may want to visit, in ranked order(ranked by potential COVID spread)
	int [] ranks;	//Array of ranks of placesToVisit
	
	public PermissionToVisit() {	//Constructor to initialize private variables
		placesToVisit = new String[] {"airport", "spa", "salon", "restaurant", "gym", "church", "mosque", "supermarket"};
		ranks = new int[] {5, 5, 5, 4, 3, 2, 2, 1};
	}
	
	public int getPlace(Scanner keyboard) {		//Method to find the place the passenger intends to visit and it's rank
		
		int rank = -1;
		
		boolean choiceEntered = false;	//Flag to check whether the choice entered was one of the list items
			
		while (!choiceEntered) {	//Loop until the choice entered is valid
			
			//Display a list of popular places in KL and input the user's choice
			System.out.println("\nPlaces You May Visit (enter number of the place): \n1. Tesco (Supermarket)  \n2. Wildflowers Restaurant & Bar \n3. Ministry of Burn (Gym) \n4. Spa Village \n5. Subang Airport \n6. None\n");
			int choiceAndRank = keyboard.nextInt();	//The list is in rank order, therefore the number entered is also the place's rank
			keyboard.nextLine();	//Used to consume the \n that was not consumed by the nextInt()
			
			if (choiceAndRank <= 6 && choiceAndRank >= 1) {	//Check whether the choice is valid
				choiceEntered = true;	//Allow the loop to end after this iteration
			
				if (choiceAndRank == 6) {	//Check whether the choice was none
					PermissionToVisit placeType = new PermissionToVisit();	//Create an object of type PermissionToVisit
					rank = placeType.findPlaceType(keyboard);	//Call findPlaceType to get the rank of the type of place and assign it to variable rank
				}
				else {	//If the choice is not none
					rank = choiceAndRank;	//Assign the user's choice to variable rank
				}
			}
			else {	//If the choice was not valid
				System.out.println("\nPlease Enter A Valid Choice(1 to 6).");	//Ask the user to try again
			}
		}
		
		return rank;	//Return the rank of the place
	}
	
	public int findPlaceType(Scanner keyboard) {	//Method to find the rank of the type of place the user wants to go
		
		//Input the type of place
		System.out.print("\nType of Place To Visit (eg: restaurant): ");
		String place = keyboard.nextLine();
		place = place.toLowerCase();
		
		//Loop through the array placeToVisit to search for the place the user entered
		for (int i = 0; i < placesToVisit.length; i++) {
			if (placesToVisit[i].equals(place)) {	//If the place is found
				return this.ranks[i];	//Return its rank
			}
		}
		return 0;	//If the place is not found, return zero
	}
	
	public int assignRank(String country, int placeRank) {		//Method to assign the overall rank of COVID spread probability, depending on the user's country and the place they visit 
		PermissionToVisit allowedToGo = new PermissionToVisit();	//Create an object of PermissionToGo
		int countryRank = allowedToGo.findCountry(country);		//Call the method findCountry from CountryRank (the superclass) to find the country rank
		
		int covidSpreadRank;	//The overall rank of COVID spread
		
		switch (countryRank) {	//Nested switch statements, first on the country's rank and the on the places's rank
		case 1:
			switch (placeRank) {
			case 1:
				covidSpreadRank = 1;
				break;
			
			case 2:
				covidSpreadRank = 1;
				break;
				
			case 3:
				covidSpreadRank = 2;
				break;
				
			case 4:
				covidSpreadRank = 2;
				break;
				
			case 5:
				covidSpreadRank = 3;
				break;
				
			default:
				covidSpreadRank = 1;
			}
			break;
			
		case 2:
			switch (placeRank) {
			case 1:
				covidSpreadRank = 1;
				break;
				
			case 2:
				covidSpreadRank = 2;
				break;
				
			case 3:
				covidSpreadRank = 2;
				break;
				
			case 4:
				covidSpreadRank = 3;
				break;
				
			case 5:
				covidSpreadRank = 3;
				break;
				
			default:
				covidSpreadRank = 2;
			}
			break;
			
		case 3:
			switch (placeRank) {
			case 1:
				covidSpreadRank = 2;
				break;
				
			case 2:
				covidSpreadRank = 2;
				break;
				
			case 3:
				covidSpreadRank = 3;
				break;
				
			case 4:
				covidSpreadRank = 4;
				break;
				
			case 5:
				covidSpreadRank = 4;
				break;
				
			default:
				covidSpreadRank = 3;
			}
			break;
			
		case 4:
			switch (placeRank) {
			case 1:
				covidSpreadRank = 3;
				break;
				
			case 2:
				covidSpreadRank = 3;
				break;
				
			case 3:
				covidSpreadRank = 4;
				break;
				
			case 4:
				covidSpreadRank = 5;
				break;
				
			case 5:
				covidSpreadRank = 5;
				break;
				
			default:
				covidSpreadRank = 4;
			}
			break;
			
		case 5:
			switch (placeRank) {
			case 1:
				covidSpreadRank = 4;
				break;
				
			case 2:
				covidSpreadRank = 4;
				break;
				
			case 3:
				covidSpreadRank = 5;
				break;
				
			case 4:
				covidSpreadRank = 5;
				break;
				
			case 5:
				covidSpreadRank = 5;
				break;
				
			default:
				covidSpreadRank = 5;
			}
			break;
			
		default:
			covidSpreadRank = 3; 
		}
		
		return covidSpreadRank;		//Return the overall rank of COVID spread probability
	}
	
	public void Permission(String country, Scanner keyboard) {	//Method to determine whether the user has permission to visit the location, based on the overall rank
		boolean permit = false;	//Flag to check whether the user has permission to travel to the location
		
		int placeRank = getPlace(keyboard);	//Call method get place to find the rank of the place to be visited
		
		if (placeRank != -1) {		//If the user wants to go somewhere
			int CovidRank = assignRank(country, placeRank);		//Call method assignRank to get the overall rank
			System.out.println("\nProbability Ranking for COVID-19 Spread: " + CovidRank);	//Display the user's overall rank
		
			if (CovidRank <= 3) {	//If the overall rank is not very high
				permit = true;	//Allow the user to go there
			}
			
			if (permit) {	//If the user has permission to go, display that.
				System.out.println("\nYou may travel to this location.\nPlease comply with COVID-19 safety regulations.");
			}
			else {	//Otherwise, tell them they may not go
				System.out.println("\nDue to the high probability of COVID-19 spread in this location, you do not have permission to travel there.");
			}
		}
	}
}

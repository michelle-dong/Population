import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Michelle Dong
 *	@since	1/10/23
 */
public class Population {
	private SortMethods sm=new SortMethods();
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	//scanner
	private Scanner kb=FileUtils.openToRead("usPopData2017.txt").useDelimiter("[\t\n]");
	
	//main method
	public static void main(String[]args){
		Population p=new Population();
		p.printIntroduction();
		p.run();
	}
	
	//time
	long startMillisec, endMillisec;
	
	//the main method that runs all the sorts and collects all the inputs
	public void run(){
		read();
		int input=0;
		//make sure the user doesn't want to quit
		while(input!=9){
			System.out.println();
			printMenu();
			//prompt the user for a number to decide what to do
			input=Prompt.getInt("\nEnter selection");
			if(input==1) {
				//selection sort
				startMillisec = System.currentTimeMillis();
				sm.selectionSort(cities);
				endMillisec = System.currentTimeMillis();
				//prints out the fifty least populous cities
				System.out.println("\nFifty least populous cities");
				printResults(0,1);
			}
			else if(input==2) {
				//merge sort
				startMillisec = System.currentTimeMillis();
				sm.mergeSort(cities);
				endMillisec = System.currentTimeMillis();
				//prints out the fifty most populous cities
				System.out.println("\nFifty most populous cities");
				printResults(31764,-1);
			}
			else if(input==3) {
				//insertion sort
				startMillisec = System.currentTimeMillis();
				sm.insertionSort(cities);
				endMillisec = System.currentTimeMillis();
				//prints out the first fifty cities sorted by city name
				System.out.println("\nFifty cities sorted by name");
				printResults(0,1);
			}
			else if(input==4){
				//merge sort but using the method mergeSortR which sorts by name
				startMillisec = System.currentTimeMillis();
				sm.mergeSortR(cities);
				endMillisec = System.currentTimeMillis();
				//prints out the last fifty cities sorted by city name
				System.out.println("\nFifty cities sorted by name descending");
				printResults(31764,-1);
			}
			else if(input==5) {
				//finds input for an existing state
				String str="";
				boolean bool=true;
				while(bool){
					str=Prompt.getString("Enter state name (ie. Alabama)");
					for(int i=0;i<31765;i++){
						//if the name is found to exist, then both exits out of both loops
						if(str.equals(cities.get(i).getState())) {i=32000;bool=false;}
					}
					//error saying that the city is not in the database
					System.out.println("ERROR: "+str+" is not valid");
				}
				//create a new array and add all the data that has that chosen state
				List<City> chosen=new ArrayList<City>();
				for(int i=0;i<31765;i++){
					if(str.equals(cities.get(i).getState())) chosen.add(cities.get(i));
					//if the arraylist already has values and the current read state name is not the chosen one, the exit out the for loop
					if(!str.equals(cities.get(i).getState())&&chosen.size()!=0) i=32000;
				}
				//merge sort
				sm.mergeSort(chosen);
				//prints the first fifty cities by the chosen state name sorted through increasingpopulation
				System.out.println("\nFifty cities sorted by name");
				System.out.printf("%5s%-20s%-25s%-15s%10s\n","","State","City","Type","Population");
				int num=0;
				if(chosen.size()>50) num=chosen.size()-50;
				for(int i=chosen.size()-1;i>=num;i--){
					System.out.printf("%3d: %-20s%-25s%-15s%10s\n",chosen.size()-i,chosen.get(i).getState(),
					chosen.get(i).getName(),chosen.get(i).getDesignation(),chosen.get(i).getPopulation());
				}
			}
			else if(input==6) {
				//get the input for a city name
				String str=Prompt.getString("\nEnter city name");
				List<City> chosen=new ArrayList<City>(); //new arraylist only with needed values
				//if there are existing cities with that name, then add it to the new arraylist
				for(int i=0;i<31765;i++)
					if(str.equals(cities.get(i).getName())) chosen.add(cities.get(i));
				//merge sort
				sm.mergeSort(chosen);
				//print out all the data with the cities from biggest population to least
				System.out.println("\nCity "+str+" by population");
				System.out.printf("%5s%-20s%-25s%-15s%10s\n","","State","City","Type","Population");
				for(int i=chosen.size()-1;i>=0;i--){
					System.out.printf("%3d: %-20s%-25s%-15s%10s\n",chosen.size()-i,chosen.get(i).getState(),
					chosen.get(i).getName(),chosen.get(i).getDesignation(),chosen.get(i).getPopulation());
				}
			}
		}
	}
	/**prints out the first 50 results either by increasing order or decreasing order based upon the interval
	 * 
	 * @param num       the starting number to go through the arraylist with
	 * @param interval  if it is 1 go by increasing order, if it is -1 go by decreasing order
	 **/
	public void printResults(int num,int interval){
		System.out.printf("%5s%-20s%-25s%-15s%10s\n","","State","City","Type","Population");
		for(int i=0;i<50;i++){
			System.out.printf("%3d: %-20s%-25s%-15s%10s\n",i+1,cities.get(num).getState(),
			cities.get(num).getName(),cities.get(num).getDesignation(),cities.get(num).getPopulation());
			num+=interval;
		}
		System.out.println("\nElapsed Time "+(endMillisec-startMillisec)+" milliseconds");
	}
	//reads the txt file and adds onto the arraylist cities by creating new objects of the class City
	//the parameters are read through the scanner
	public void read(){
		cities=new ArrayList<City>();
		for(int i=0;i<31765;i++){
			String state=kb.next();
			String name=kb.next();
			String designation=kb.next();
			int population=kb.nextInt();
			cities.add(new City(state,name,designation,population));
		}
	}
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
}

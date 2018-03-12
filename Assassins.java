import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Assassins{
    String fileName = "temp.txt";
    String line = null;
    String[] survivors;
    String[] sporkers;
    int day = 0;
    
    public static boolean findVal(int[] arr, int val){
	for(int x : arr)
	    if(x == val)
		return true;
	return false;
    }
    public static void printArr(String[] players){
	for(int i = 0; i < players.length; i++)
	    System.out.println(players[i] + " -> ");
    }

    public static void main(String [] args){
	int counter = 0;
	String[] players = new String[100];
	
	Scanner s = new Scanner(System.in);
	Random rand = new Random();
	
	//get names---------------------------------------------------
	System.out.println("Type the names of the players.");
	System.out.println("Type exit to quit.");
	String name = s.next();
	while(!name.equals("exit")){ //get player list
	    players[counter++] = name;
	    name = s.next();
	}
	
	//randomize---------------------------------------------------
	int[] unused = new int[counter];
	String[] randomized = new String[counter];
	for(int i = 0; i < counter; i++) //keep track of used indices
	    unused[i] = i;
	for(int i = 0; i < counter; i++){
	    int temp = rand.nextInt(counter);
	    while (!findVal(unused,temp)) //make new randomized player list
		temp = rand.nextInt(counter);
	    randomized[temp] = players[i];
	    unused[temp] = -1;
	}
	System.out.println("----------------------------");
	printArr(randomized);

	//stats----------------------------------------------------
	String[] alphabetical = randomized;
	Arrays.sort(alphabetical);
	int[] kills = new int[alphabetical.length];
	for(int i = 0; i < kills.length; i++)
	    kills[i] = 0;
	
	while(readline != null){
	    String[] temp = line.split();
	    if(temp[0].equals("Day"))
		continue;
	    for(int i = 0; i < alphabetical.length; i++)
		if(alphabetical[i].equals(temp[0]))
		    kills[i]++;
	}
	
    }
}

//Arrays.asList(yourArray).contains(yourValue);

//file stuff

try {
    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    while((line = bufferedReader.readLine()) != null){
	String[] temp = line.split();
	if(temp[0].equals("Day"))
	    day = temp[0]++;
        else if(temp[0].equals("Survivors:"))
	    
    }
    bufferedReader.close();         
}
catch(FileNotFoundException ex)
    System.out.println("Unable to open file '" + fileName + "'");                
catch(IOException ex)
    System.out.println("Error reading file '" + fileName + "'");


/*
Log.txt
Day 4
Remaining: a->b->c->
j killed k
l killed m

Day 3
*/

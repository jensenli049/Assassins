import java.io.*;
import java.lang.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Assassins{
    static String fileName = "Assassins_2018.txt";
    static String divider = "===========================================================================";
    static String line = null;
    static String[] survivors;
    static String[] sporkers;
    static int day = 0;
    
    public static boolean findVal(int[] arr, int val){
	for(int x : arr)
	    if(x == val)
		return true;
	return false;
    }
    
    public static void printArr(String[] players){
	for(int i = 0; i < players.length; i++)
	    System.out.println(players[i] + "->");
    }

    public static int countOcc (String[] arr, String name) {
	int n = 0;
	for (int x = 0; x < arr.length; x++)
	    if(arr[x].equals(name))
		n++;
	return n;
    }
    public static int indexOcc (String[] arr, String name) {
	int n = 0;
	for (int x = 0; x < arr.length; x++)
	    if(arr[x].equals(name)){
		n = x;
		break;
	    }
	return n;
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
	
	//get forked---------------------------------------------------
	counter = 0;
	System.out.println("Type the names of the assassin.");
	System.out.println("Type exit to quit.");
	name = s.next();
	while(!name.equals("exit")){ //get player list
	    if(!Arrays.asList(survivors).contains(name)){
		System.out.println("Name doesn't exist, try again.");
		printArr(survivors);
	    }
	    line += name + " killed " + survivors[(indexOcc(survivors,name)+1)%survivors.length] + "\n";
	    counter++;
	    name = s.next();
	}
	
	//log stuff
	try{
	    File file = new File(fileName);
	    file.createNewFile();
	}
	catch(IOException e){
	    e.printStackTrace();
	}
	
	//read file
	FileReader fileReader = null;
	try{
	    fileReader = new FileReader(fileName);
	}
	catch (FileNotFoundException ex){
	    System.out.println("Can't find " + fileName);
	}
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	int scount = 0;
	try{
	    while((line = bufferedReader.readLine()) != null){
		String[] temp = line.split(" ");
		if(temp[0].equals("Day"))
		    day = Integer.parseInt(temp[0])+1;
		else if(temp[0].equals("Survivors:")){
		    survivors = new String[temp.length-1];
		    System.arraycopy(temp, 1, survivors, 0, temp.length-1);
		}
		else if(temp[0].equals(divider))
		    continue;
		else{
		    sporkers = new String[randomized.length];
		    sporkers[scount++] = temp[0];
		}
	    }
	    bufferedReader.close();
	}
	catch (IOException e) {
	    System.out.println("Nothing happened");
        }
	
	//stats----------------------------------------------------
	String[] alphabetical = randomized;
	Arrays.sort(alphabetical);
	int[] kills = new int[alphabetical.length];
	for(int i = 0; i < kills.length; i++)
	    kills[i] = 0;
	
	
	for(int i = 0; i < sporkers.length; i++)
	    for(int j = 0; j < alphabetical.length; j++)
		if(alphabetical[j].equals(sporkers[i]))
		    kills[j]++;	
    }
}

//Arrays.asList(yourArray).contains(yourValue);


/*
  Log.txt
  Day 4
  Remaining: a->b->c->
  j killed k
  l killed m

  Day 3
*/

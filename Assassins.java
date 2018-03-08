import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Assassins{
    public static boolean findVal(int[] arr, int val){
	for(int x : arr)
	    if(x == val && x != 0)
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
	while(!name.equals("exit")){
	    players[counter++] = name;
	    name = s.next();
	}
	System.out.println(name.equals("exit"));
	
	//randomize---------------------------------------------------
	int[] unused = new int[counter];
	String[] randomized = new String[counter];
	for(int i = 0; i < counter; i++)
	    unused[i] = i;
	for(int i = 0; i < counter; i++){
	    int temp = rand.nextInt(counter);
	    while (!findVal(unused,temp))
		temp = rand.nextInt(counter);
	    System.out.println(counter);
	    randomized[temp-1] = players[temp-1];
	    unused[temp-1] = -1;
	}
	printArr(randomized);
    }
}

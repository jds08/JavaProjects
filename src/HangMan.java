
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangMan {
	private static int count; // Keeps track of attempts left
	private static ArrayList<Character> wordsUsed = new ArrayList();
	private static ArrayList<String> wordFile = new ArrayList();
	
	public static void main(String[] args) {
		File file = new File("textfile.txt");
		String word = "";
		try(Scanner myReader = new Scanner(file)){
			while(myReader.hasNext()) {
			wordFile.add(myReader.nextLine());
			}
			
			int i = (int)(Math.random()*wordFile.size()) ; // [ 0, n-1)
			word = wordFile.get(i);
			count = word.length();
		} catch(Exception e){
			System.out.println("File not found");
			e.getStackTrace();
		}
		
		int [] arr = new int[word.length()];
		System.out.println("------------------------");
		System.out.println("| Welcome              |");
		System.out.println("| This is Hangman !!     |");
		System.out.println("-------------------------");
		System.out.println("You have " + count + " attemps left." );
		displayWords(arr, word);
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Guess:");
			char letter = input.next().charAt(0); // stores character
			
			if((letter >= 65 && letter <= 90) || ( letter >= 97 && letter <= 122)) {
				if(!wordsAlreadyUsed(wordsUsed, letter) && !userInput(arr, word, letter)) count--;
		
				System.out.print("You have " + count + " attemps left.\t\t" );
				if( wordsAlreadyUsed(wordsUsed, letter)) {
					System.out.print("Words you already used: ");
					for( char l: wordsUsed) {
						System.out.print(l  + " ");
					}
					System.out.println("\n\n\n");
				}
				if(count == 0) {
					System.out.println("Sorry you loss :)");
					break;
				}
				displayWords(arr, word);
				if(!spaceStillEmpty(arr) && count > 0) {
					System.out.println("You won!!");
				}
			} else {
				System.out.println("Not a word try again.");
			}
			
		} while(spaceStillEmpty(arr));
		
	
	}
	/**
	 * The spaceStillEmpty method will check if there are words that haven't been discover yet.
	 * @param checker 
	 */
	public static boolean spaceStillEmpty(int [] checker) {
		for(int i = 0; i < checker.length; i++ ) {
			if(checker[i] == 0) return true;
			}
		return false;
	}
	/**
	 * This method uses the checker array to check which words it should not display. The words that are not supposed to be display hold a value of 0 in the checker's array. 
	 * @param checker
	 * @param w
	 */
	public static void displayWords(int [] checker, String w) {
		for(int i = 0; i < checker.length; i++ ) {
			if(checker[i] == 0 ) { 
				if(i == 0 ) {
					System.out.print("_");
				}else {
					System.out.print(" _");
				}
			}
			else {
				if(i == 0 || i == checker.length -1) {
					System.out.print(w.charAt(i) );
				}else { 
					System.out.print(" " +w.charAt(i));
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * If user guesses a word correctly the index of the corresponding element in the checker int array  will be change to 1. Otherwise nothing is changed.
	 * @param checker
	 * @param w
	 * @param c
	 * @return
	 */
	public static boolean userInput(int [] checker, String w, char c) {
		boolean user = false;
		for(int i = 0; i < checker.length; i++) {
			if(c == w.charAt(i)) {
				checker[i] = 1;
				user = true;
			}
		}
		return user;
	}
	public static boolean wordsAlreadyUsed(ArrayList<Character> arr , char l) {
		for( char letter: arr) {
			if(letter == l) {
				return true;
			}
		}
		arr.add(l);
		return false;
	}
}
package ie.gmit.sw;

import java.util.Scanner;

/**
 * 
 * @author Tomas Brazas
 * @version 1.0
 * @since 1.8
 * 
 */

public class Menu {
	
	Scanner scanner = new Scanner(System.in);
	/**
	 * 
	 * <code>Public Menu</code>
	 * <p>
	 * Creates a console based UI for user input to calculate cosine distance
	 * between two files. 
	 * </p>
	 * 
	 */

	public Menu() throws InterruptedException {
		
		
		// Header
		System.out.println("*** Cosine Distance Calculator *** \n");

		// User input
		System.out.print("Enter File Name 1 \n> ");
		String f2 = scanner.nextLine();
		System.out.print("Enter File Name 2 \n> ");
		String f1 = scanner.nextLine();
		
		// New instance of Processor
		System.out.println("Calculating... Please wait.");
		new Processor().process(f1, f2);	

	}
	
}

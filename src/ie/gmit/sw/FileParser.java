package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author Tomas Brazas
 * 
 */


public class FileParser implements Runnable {
	
	//Local Variables
	private String file;
	private BlockingQueue<Shingle> queue;
	private int shingleSize = 3;
	private Deque<String> buffer = new LinkedList<>();
	private int id;
	
	public FileParser(String file, int shingleSize, BlockingQueue<Shingle> queue, int id) {
		super();
		this.file = file;
		this.shingleSize = shingleSize;
		this.queue = queue;
		this.id = id;
	}

	public void run() {
		try {
			// Creating a BufferedReader object to read in the file
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				// BufferedReader reads in each line of the file and checks the line is greater than 0 
				if (line.length() > 0) {
					//Converts the string to lowercase
					line = line.toLowerCase().replaceAll("[^A-Za-z0-9]", " ");
					//Split the line into seperate words
					String[] words = line.split("");
					addWordsToBuffer(words);
					Shingle s = getNextShingle();
					queue.put(s);
				}	
			}
			br.close();
			flushBuffer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <code>void run()</code>
	 * 
	 * <p>
	 * Reads all lines from a file and skips the blank lines. Changes the lines to lower case, 
	 * replaces and non-alphanumeric characters and splits the line into words when there is a space between characters. 
	 * Words are then passed to the <code>addWordsToBuffer()</code> method. 
	 * Creates a new <i>Shingle</i> object and then adds it to the queue.
	 * </p>
	 * 
	 */
	
	private void addWordsToBuffer(String[] words) {
		for (String s: words) {
			buffer.add(s);
		}
	}
	
	/**
	 * Adds words to a list that will be used to create Shingle object.
	 *	
	 * @return Linked list.
	 */
	
	
	
	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek()!=null) {
				sb.append(buffer.poll());
				counter++;
			}
			else {
				counter = shingleSize;
			}
			
		}
		if(sb.length() > 0) {
			return (new Shingle(id,sb.toString().hashCode()));
		}
		else {
			return null;
		}
		
	} // getNextShingle
	
	/**
	 * Creates a new Shingle object.  
	 * 
	 * @return	a new <b>Shingle</b> with id and the hashCode of a string of words in the StringBuilder.
	 * 
	 */
	
	private void flushBuffer() throws InterruptedException{
		while(buffer.size() > 0) {
			Shingle s = getNextShingle();
			if(s != null) {
				queue.put(s);
			}
		} // While
		queue.put(new Poison(id, 0));
	} // flushBuffer

}

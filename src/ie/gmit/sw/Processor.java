package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author Tomas Brazas
 * 
 */

public class Processor {
	
	
	
	/**
	 * 
	 * <code>void process()</code>
	 * 
	 * @param f1
	 *            first document
	 * @param f2
	 *            second document
	 * @throws InterruptedException
	 *             throws interrupted    
	 * 
	 * <p>
	 * <b>Thread</b> created for each file document and a new <i>FileParser</i>
	 * object is created to pass the given parameters.
	 * <b>Thread</b> instantiates a <i>ShingleTaker</i> object on the <b>thread</b>. 
	 * The <b>threads</b> are combined for parallel operation.
	 * </p>
	 *       
	 */
	
	public void process(String f1, String f2) throws InterruptedException {
		
		BlockingQueue<Shingle> queue = new LinkedBlockingQueue<Shingle>(100);
		int shingleSize = 3;
		
		Thread t1 = new Thread(new FileParser(f1, shingleSize, queue, 1));
		Thread t2 = new Thread(new FileParser(f2, shingleSize, queue, 2));
		
		// Declaring a new Thread and creating a ShingleTaker object 
		Thread t3 = new Thread(new ShingleTaker(queue, 2));
		
		// New object
		CosineDistance c = new CosineDistance();
		
		// Thread operations
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		
		
		// Output Cosine Similarity on Console
		System.out.println("Cosine Distance: " + c.getDotProduct() * 100 + " %.");
		
	}
}

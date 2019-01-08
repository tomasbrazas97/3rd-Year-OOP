package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Tomas Brazas
 * 
 */

public class ShingleTaker implements Runnable{

	//Local Variables
	private Map<Integer, Integer> f1 = new TreeMap<>();
	private Map<Integer, Integer> f2 = new TreeMap<>();
	private BlockingQueue<Shingle> queue;
	private int fileCount;
	private boolean keepRunning = true;
	private ExecutorService pool = Executors.newFixedThreadPool(3);
    double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

	public Map<Integer, Integer> getMap() {
		return f1;
	}
	
	public ShingleTaker(BlockingQueue<Shingle> queue, int fileCount) {
		super();
		this.queue = queue;
		this.fileCount = fileCount;
	}

	@Override
	public void run() {
		while (fileCount > 0 && keepRunning) {
			try {
				Shingle s = queue.take();
				if (s instanceof Poison) {
					fileCount--;
				} else {
					pool.execute(new Runnable() {
						public void run() {
							List<Integer> list = null;
							int shingle = s.getHashcode();
							if (s.getId() == 1) {
								if (!f1.containsKey(shingle)) {
									//if ()
									list = new ArrayList<Integer>();
									Integer n = f1.get(shingle);
									n = (n == null) ? 1 : ++n;
									list.add(n);
									f1.get(shingle);
									f1.put(shingle, n);
								}
							}
							if (s.getId() == 2) {
								if (!f2.containsKey(shingle)) {
									//if ()
									list = new ArrayList<Integer>();
									Integer n = f2.get(shingle);
									n = (n == null) ? 1 : ++n;
									list.add(n);
									f2.get(shingle);
									f2.put(shingle, n);
								}
							}
						
						}
					});
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Map<Integer, Integer> s = f1;
		Map<Integer, Integer> t = f2;
		
		HashSet<Integer> intersection = new HashSet<>(s.keySet());
        intersection.retainAll(t.keySet());
		
        dotProduct += s.get(1) * t.get(1);
        magnitudeA += Math.pow(s.get(s.keySet()), 2);
        magnitudeB += Math.pow(t.get(t.keySet()), 2);
		
		new CosineDistance().setDotProduct(dotProduct / Math.sqrt(magnitudeA * magnitudeB));
	}//run

}

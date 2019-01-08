package ie.gmit.sw;

/**
 * 
 * @author Tomas Brazas
 * 
 */

public class Index {
	
	//Local variables
	private int frequency = 0; 
	private String fileName;
	
	//getter/setters generated
	public Index(int frequency) {
		super();
		this.frequency += frequency;
	}
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 
}

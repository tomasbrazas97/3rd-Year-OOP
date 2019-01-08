package ie.gmit.sw;

public class Shingle {
	private int id;
	private int hashcode;

	//Constructor & Getters/Setters
	/**
	 * <b>Constructors, getters and setters</b>
	 * @returns id's & hashcode's
	 */
	public Shingle() {
		super();
		
	}
	
	public Shingle(int id, int hashcode) {
		super();
		this.id = id;
		this.hashcode = hashcode;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHashcode() {
		return hashcode;
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
}

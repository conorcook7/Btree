
public class Sequence {

	
	private String key;
	private int duplicates;
	
	
	public Sequence(String key) {
		this.key = key;
		this.duplicates = 1;
	}
	
	public void duplicate() {
		this.duplicates++;
	}
	
	public int getDuplicates() {
		return this.duplicates;
	}
	
	public String getKey() {
		return this.key;
	}
	
	
	
	public String toString() {
		return key + ": " + getDuplicates();
	}
}



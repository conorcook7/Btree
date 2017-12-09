
public class Sequence {

	
	private String key;
	private int duplicates;
	
	
	public Sequence(String key) {
		this.key = key;
		this.duplicates = 0;
	}
	
	public void duplicate() {
		this.duplicates++;
	}
	
	public String getKey() {
		return this.key;
	}	
	
	
}

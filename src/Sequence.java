
/**
 * This is a treeObject class that contains the key sequence and keeps track of
 * the amount of duplicates there are per Sequence
 * 
 * @author conor cook, zach garner, michael boyle
 *
 */
public class Sequence {

	private String key;
	private int duplicates;

	/**
	 * Constructor
	 * 
	 * @param key
	 */
	public Sequence(String key) {
		this.key = key;
		this.duplicates = 0;
	}

	/**
	 * Second Constructor (meant if you know dup. amount)
	 * 
	 * @param key
	 * @param duplicates
	 */
	public Sequence(String key, int duplicates) {
		this.key = key;
		this.duplicates = duplicates;
	}

	/**
	 * Simple method to increment duplicate sequences
	 */
	public void duplicate() {
		this.duplicates++;
	}

	/**
	 * Simple getter method that gets the duplicate amount that the sequence holds
	 * 
	 * @return this.duplicates
	 */
	public int getDuplicates() {
		return this.duplicates;
	}

	/**
	 * Simple getter method to get the sequence key
	 * 
	 * @return this.key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * This method overrides the existing toString to a new and improved version :)
	 */
	public String toString() {
		return key + ": " + getDuplicates();
	}
}


/**
 * This class is to create a type of a struct that contains two elements of
 * different types. This is what is known as a Tuple, java does not have their
 * tuple, so we improvised :)
 * 
 * @author conor cook, zach garner, michael boyle
 *
 * @param <L>
 * @param <R>
 */
public class Tuple<L, R> {

	private L l;
	private R r;

	/**
	 * Constructor
	 * 
	 * @param l
	 * @param r
	 */
	public Tuple(L l, R r) {
		this.l = l;
		this.r = r;
	}

	/**
	 * Returns left element of the Tuple
	 * 
	 * @return
	 */
	public L l() {
		return this.l;
	}

	/**
	 * Returns right element in the Tuple
	 * 
	 * @return
	 */
	public R r() {
		return this.r;
	}
}

// "Tuple who?" -James Gosling
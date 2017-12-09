
public class Tuple<L, R> {

	private L l;
	private R r;
	
	
	public Tuple(L l, R r) {
		this.l = l;
		this.r = r;
	}
	
	
	public L l() {
		return this.l;
	}
	
	public R r() {
		return this.r;
	}
	
	
	
}

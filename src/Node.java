
import java.util.ArrayList;

public class Node {
	
	
	private long index;
	private long parent;
	private ArrayList<Long> children;
	private ArrayList<Sequence> elems;
	
	
	private Node(long index, long parent, ArrayList<Long> children, ArrayList<Sequence> elems) {
		this.index = index;
		this.parent = parent;
		this.children = children;
		this.elems = elems;
	}
	
	public boolean isLeaf() {
		return this.children.size() == 0;
	}
	
	public void add(String key) {
		
	}
	
	public Sequence search(String key) {
		for (Sequence elem : elems) {
			if (elem.getKey() == key)
				return elem;
		}
		return null;
	}
	
	public int elemNum() {
		return elems.size();
	}
	
	public long nextIndex(String key) {
		for (int i = 0; i < elems.size(); i++) {
			if (key.compareTo(elems.get(i).getKey()) < 0)
				return i;
		}
		return elems.size();
	}
	
}
























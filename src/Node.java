
import java.util.ArrayList;

public class Node {
	
	
	private long index;
	private long parent;
	private ArrayList<Long> children;
	private ArrayList<Sequence> elems;
	
	
	public Node(long index, long parent, ArrayList<Long> children, ArrayList<Sequence> elems) {
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
	
	public void add(Sequence s) {
		
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
	
	public Tuple<ArrayList<Sequence>, ArrayList<Sequence>> split() {
		int middle = Math.floorDiv(elems.size(), 2);
		ArrayList<Sequence> left = (ArrayList<Sequence>) elems.subList(0, middle);
		ArrayList<Sequence> right = (ArrayList<Sequence>) elems.subList(middle + 1, elems.size() - 1);
		return new Tuple<ArrayList<Sequence>, ArrayList<Sequence>>(left, right);
	}
	
	public Sequence middle() {
		return elems.get(Math.floorDiv(elems.size(), 2));
	}
	
	public long getParent() {
		return this.parent;
	}
	
	public void removeChild(Node n) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) == n.index) {
				children.remove(i);
			}
		}
	}
	
	public void addChild(Node n, int i) {
		children.add(i, n.index);
	}
	
	public Integer indexOf(Sequence s) {
		for (int i = 0; i < elems.size(); i++) {
			if (elems.get(i).equals(s))
				return i;
		}
		return null;
	}
	
	public long getIndex() {
		return this.index
;	}
}

























import java.util.ArrayList;

public class Node {
	
	
	private int index;
	private int parent;
	private ArrayList<Sequence> elems;
	private ArrayList<Integer> children;
	
	
	private Node(int index, int parent, ArrayList<Integer> children, ArrayList<Sequence> elems) {
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
	
	
	public long nextIndex(String key) {
		for (int i = 0; i < elems.size(); i++) {
			if (key.compareTo(elems.get(i).getKey()) < 0)
				return i;
		}
		return elems.size();
	}
	
}
























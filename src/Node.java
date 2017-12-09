
import java.util.ArrayList;

public class Node {
	
	
	private int index;
	private Node parent;
	private ArrayList<Sequence> elems;
	private ArrayList<Node> children;
	
	
	private Node(int index, Node parent, ArrayList<Node> children, ArrayList<Sequence> elems) {
		this.index = index;
		this.parent = parent;
		this.children = children;
		this.elems = elems;
	}
	
	public boolean isLeaf() {
		return this.children == null;
	}
	
	public Node nextNode(String key) {
		return children.get(nextIndex(key));
	}
	
	public void add(String key) {
		elems.add(nextIndex(key), new Sequence(key));
	}
	
	public Sequence search(String key) {
		for (Sequence elem : elems) {
			if (elem.getKey() == key)
				return elem;
		}
		return null;
	}
	
	
	private int nextIndex(String key) {
		for (int i = 0; i < elems.size(); i++) {
			if (key.compareTo(elems.get(i).getKey()) < 0)
				return i;
		}
		return elems.size();
	}
	
}
























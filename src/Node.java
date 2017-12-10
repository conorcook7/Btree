//
//import java.util.ArrayList;
//
//public class Node {
//	
//	
//	private Long parent;
//	private ArrayList<Long> children;
//	private ArrayList<Sequence> elems;
//	
//	
//	public Node(Long parent, ArrayList<Long> children, ArrayList<Sequence> elems) {
//		this.parent = parent;
//		this.children = children;
//		this.elems = elems;
//	}
//	
//	public boolean isLeaf() {
//		return this.children.size() == 0;
//	}
//	
//	public void add(String key) {
//		elems.add(nextIndex(key), new Sequence(key));
//	}
//	
//	public void add(Sequence s) {
//		elems.add(nextIndex(s.getKey()), s);
//	}
//	
//	public Sequence search(String key) {
//		for (Sequence elem : elems) {
//			if (elem.getKey() == key)
//				return elem;
//		}
//		return null;
//	}
//	
//	public int elemNum() {
//		return elems.size();
//	}
//	
//	public int nextIndex(String key) {
//		for (int i = 0; i < elems.size(); i++) {
//			if (key.compareTo(elems.get(i).getKey()) < 0)
//				return i;
//		}
//		return elems.size();
//	}
//	
//	public Tuple<ArrayList<Sequence>, ArrayList<Sequence>> split() {
//		int middle = Math.floorDiv(elems.size(), 2);
//		ArrayList<Sequence> left = (ArrayList<Sequence>) elems.subList(0, middle);
//		ArrayList<Sequence> right = (ArrayList<Sequence>) elems.subList(middle + 1, elems.size() - 1);
//		return new Tuple<ArrayList<Sequence>, ArrayList<Sequence>>(left, right);
//	}
//	
//	public Tuple<ArrayList<Long>, ArrayList<Long>> splitChildren() {
//		int middle = Math.floorDiv(children.size(), 2);
//		ArrayList<Long> left = (ArrayList<Long>) children.subList(0, middle);
//		ArrayList<Long> right = (ArrayList<Long>) children.subList(middle + 1, children.size() - 1);
//		return new Tuple<ArrayList<Long>, ArrayList<Long>>(left, right);
//	}
//	
//	public Sequence middle() {
//		return elems.get(Math.floorDiv(elems.size(), 2));
//	}
//	
//	public Long getParent() {
//		return this.parent;
//	}
//	
//	public void removeChild(Long n) {
//		children.remove(n);
//	}
//	
//	public void addChild(Long n, int i) {
//		children.add(i, n);
//	}
//
//	public Integer indexOf(String key) {
//		for (int i = 0; i < elems.size(); i++) {
//			if (elems.get(i).equals(key))
//				return i;
//		}
//		return null;
//	}
//		
//	public Long getChild(int i) {
//		return this.children.get(i);
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

import java.io.File;
import java.io.RandomAccessFile;



@SuppressWarnings("unused")
public class TestTree {

	
	private int degree;
	private RandomAccessFile file;
	private Node root;
	
	
	public TestTree(int degree, String path) {
		this.degree = degree;
		File f = new File(path);
		try {
			f.createNewFile();
			this.file = new RandomAccessFile(f, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.root = getRoot();
	}
	
	
	public void add(String key) {
		
	}
	
	public Sequence findNode(String key) {
		return null;
	}	
	
	
	private class Node {
		
		private int index;
		private Node parent;
		private Node[] children;
		private Sequence[] elems;
		
	}
	
	
	private Node readNode(int i) {
		return null;
	}
	
	private void writeNode(Node n) {
		
	}
	
	private Node getRoot() {
		return null;
	}
	
}











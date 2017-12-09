import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;



@SuppressWarnings("unused")
public class TestTree {

	
	private RandomAccessFile file;
	
	
	public TestTree(String path) throws FileNotFoundException {
		File f = new File(path);
		this.file = new RandomAccessFile(f, "rw");
	}
	
	public TestTree(String path, int degree) throws FileAlreadyExistsException {
		File f = new File(path);
		if (f.isFile()) {
		    throw new FileAlreadyExistsException("File already exists");         
		}
		try {
			f.createNewFile();
			this.file = new RandomAccessFile(f, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDegree(degree);
	}
	
	
	public void insert(String key) {
		Sequence s = search(key);
		if (s != null)
			s.duplicate();
	    else {
	    	
	    }
	}
	
	public Sequence search(String key) {
		return null;
	}
	
	
	public class Node {
		
		public int index;
		public Node parent;
		public Node[] children;
		public Sequence[] elems;
		
		
		public Node(int index, Node parent, Node[] children, Sequence[] elems) {
			this.index = index;
			this.parent = parent;
			this.children = children;
			this.elems = elems;
		}
		
	}
	
		
	private void put(Node n) {
		
	}
		
	private Node get(int i) {
		return null;
	}
	
	
	private Node getRoot() {
		return null;
	}
	
	private int getDegree() {
		return -1;
	}
	
	private void setDegree(int d) {
		
	}
	
	private void setEnd() {
		
	}
	
	private int getEnd() {
		return -1;
	}
	
}











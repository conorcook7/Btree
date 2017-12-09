import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;



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
	    	s = new Sequence(key);
	    	Node current = getRoot();
	    	while (!current.isLeaf()) {
	    		current = current.nextNode(s);
	    	}
	    	current.add(s);
	    	balance(current);
	    }
	}
	
	public Sequence search(String key) {
		return null;
	}
		
	
	private Tuple<Node, Sequence> maybeSearch(String key) {
		Node current = getRoot();
		while (!current.isLeaf()) {
			Sequence s = current.search(key);
			if (s != null) {
				return new Tuple<Node, Sequence>(current, s);
			}
			current = current.nextNode(key);
		}
		Sequence s = current.search(key);
		if (s != null) {
			return new Tuple<Node, Sequence>(current, s);
		}
		else {
			return new Tuple<Node, Sequence>(current, null);
		}
	}
	
	private void balance(Node s) {
		
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
	
	private int getEnd() {
		return -1;
	}
	
	private void setEnd() {
		
	}
				
}











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
		// make empty root node
	}
	
	
	public void insert(String key) {
		Tuple<Node, Sequence> result = maybeSearch(key);
		Node resNode = result.l();
		Sequence resSeq = result.r();
		if (resSeq != null) 
			resSeq.duplicate();
		else
			resNode.add(key);
			splitChild(resNode);
	}
	
	public Sequence search(String key) {
		return maybeSearch(key).r();
	}
	
	
	private Tuple<Node, Sequence> maybeSearch(String key) {
		return maybeSearchHelper(key, getRoot());
	}
	
	private Tuple<Node, Sequence> maybeSearchHelper(String key, Node curNode) {
		Sequence result = curNode.search(key);
		if (result != null)
			return new Tuple<Node, Sequence>(curNode, result);
		else if (curNode.isLeaf())
			return new Tuple<Node, Sequence>(curNode, null);
		else
			return maybeSearchHelper(key, readNode(curNode.nextIndex(key)));
	}
	
	
	private void splitChild(Node n) {
		if (n.elemNum() >= 2 * getDegree() - 1) {
			Tuple<ArrayList<Sequence>, ArrayList<Sequence>> seqs = n.split();
			ArrayList<Sequence> left = seqs.l();
			ArrayList<Sequence> right = seqs.r();
			Sequence middle = n.middle();
			Node parent = readNode(n.getParent());
			
			parent.add(middle);
			parent.removeChild(n);
			int middleIndex = parent.indexOf(middle);
			
			parent.addChild(createNode(parent, null/****/, left), middleIndex);
			parent.addChild(createNode(parent, null/****/, right), middleIndex + 1);
			
			
		}
	}
	
	private Node createNode(Node parent, ArrayList<Node> children, ArrayList<Sequence> seqs) {
		return null; //make sure to write node
	}
	
	private Node readNode(long index) {
		return null;
	}
	
	private void writeNode(Node n) {
		
	}
	
	
	private long getRoot() {
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
	
	private boolean isRoot(Node n) {
		return n.equals(getRoot())
	;}
				
}











import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;


/**
 * 
 * @author conor cook, zach garner, michael boyle
 *
 */
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
		createNode(null, null, null);
	}
	
	
	public void insert(String key) {
		Tuple<Long, Sequence> result = maybeSearch(key);
		Node resNode = readNode(result.l());
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
	
	
	private Tuple<Long, Sequence> maybeSearch(String key) {
		return maybeSearchHelper(key, getRoot());
	}
	
	private Tuple<Long, Sequence> maybeSearchHelper(String key, Long curIndex) {
		Node curNode = readNode(curIndex);
		Sequence result = curNode.search(key);
		if (result != null)
			return new Tuple<Long, Sequence>(curIndex, result);
		else if (curNode.isLeaf())
			return new Tuple<Long, Sequence>(curIndex, null);
		else
			return maybeSearchHelper(key, curNode.getChild(curNode.nextIndex(key)));
	}
	
	private void splitChild(Node n) {
		if (n.elemNum() >= 2 * getDegree() - 1) {
			Tuple<ArrayList<Sequence>, ArrayList<Sequence>> seqs = n.split();
			Tuple<ArrayList<Long>, ArrayList<Long>> children = n.splitChildren();
			ArrayList<Sequence> left = seqs.l();
			ArrayList<Sequence> right = seqs.r();
			ArrayList<Long> leftChildren = children.l();
			ArrayList<Long> rightChildren = children.r();
			Sequence middle = n.middle();
			Node parent = readNode(n.getParent());
			
			parent.add(middle);
			parent.removeChild(n);
			int middleIndex = parent.indexOf(middle);
			Node newLeft = createNode(parent.getIndex(), leftChildren, left);
			Node newRight = createNode(parent.getIndex(), rightChildren, right);
			
			parent.addChild(newLeft, middleIndex);
			parent.addChild(newRight, middleIndex + 1);
			
			writeNode(newLeft);
			writeNode(newRight);
			writeNode(parent);
			
			splitChild(parent);
		}
	}
	
	private Node createNode(Long parent, ArrayList<Long> children, ArrayList<Sequence> seqs) {
		Node newNode = new Node(getEnd(), parent, children, seqs);
		writeNode(newNode);
		return newNode;
	}
	
	private Node readNode(Long index) {
		return null;
	}
	
	private void writeNode(Node n) {
		
	}
	
	
	private Long getRoot() {
		return null;
	}
	
	private int getDegree() {
		return -1;
	}
	
	private void setDegree(int d) {
		
	}
	
	private Long getEnd() {
		return null;
	}
	
	private void setEnd() {
		
	}
	
	private boolean isRoot(Node n) {
		return n.getIndex().equals(getRoot());
	}
				
}











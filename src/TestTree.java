import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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
	
	private final int INT_BYTES = 4;
	private final int LONG_BYTES = 8;
	private final int CHAR_BYTES = 2;
	
	private final long DEGREE_INDEX = 0;
	private final long SEQ_LENGTH_INDEX = DEGREE_INDEX + INT_BYTES;
	private final long ROOT_INDEX = SEQ_LENGTH_INDEX + INT_BYTES;
	private final long END_INDEX = ROOT_INDEX + LONG_BYTES;
	//private final long TREE_START = END_INDEX + LONG_BYTES;
	
	private final long PARENT_OFFSET = 0;
	private final long NUM_CHILDREN_OFFSET = PARENT_OFFSET + LONG_BYTES;
	private final long NUM_ELEMS_OFFSET = NUM_CHILDREN_OFFSET + INT_BYTES;
	//private final long ELEMS_START = NUM_ELEMS_OFFSET + INT_BYTES;
	
	
	public TestTree(String path) throws FileNotFoundException {
		File f = new File(path);
		this.file = new RandomAccessFile(f, "rw");
	}
	
	public TestTree(String path, int degree, int sequenceLength) throws FileAlreadyExistsException {
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
		Node rootNode = new Node(null, null, null);
		long rootIndex = writeNode(rootNode);
		setRoot(rootIndex);
	}
	
	
	public void insert(String key) {
		Tuple<Long, Sequence> result = maybeSearch(key);
		Node resNode = readNode(result.l());
		Sequence resSeq = result.r();
		if (resSeq != null) 
			resSeq.duplicate();
		else
			resNode.add(key);
			splitChild(result.l());
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
	
	private void splitChild(Long n) {
		Node node = readNode(n);
		if (node.elemNum() >= 2 * getDegree() - 1) {
			Tuple<ArrayList<Sequence>, ArrayList<Sequence>> seqs = node.split();
			Tuple<ArrayList<Long>, ArrayList<Long>> children = node.splitChildren();
			ArrayList<Sequence> left = seqs.l();
			ArrayList<Sequence> right = seqs.r();
			ArrayList<Long> leftChildren = children.l();
			ArrayList<Long> rightChildren = children.r();
			Sequence middle = node.middle();
			Node parent = readNode(node.getParent());
			
			parent.add(middle);
			parent.removeChild(n);
			int middleIndex = parent.indexOf(middle.getKey());
			Node newLeft = new Node(node.getParent(), leftChildren, left);
			Node newRight = new Node(node.getParent(), rightChildren, right);

			long lIndex = writeNode(newLeft);
			long rIndex = writeNode(newRight);
			
			parent.addChild(lIndex, middleIndex);
			parent.addChild(rIndex, middleIndex + 1);
			
			writeNode(parent);
			
			splitChild(node.getParent());
		}
	}
	
	private Node readNode(Long index) {
		Long parent = getParent(index);
		ArrayList<Long> children = new ArrayList<Long>();
		ArrayList<Sequence> elems = new ArrayList<Sequence>();
		Node n = null;
		try {
			int numChildren = file.readInt();
			int numElems = file.readInt();
			for (int i = 0; i < numChildren; i++) {
				children.add(file.readLong());
			}
			for (long i = 0; i < numElems; i++) {
				String s = "";
				for (long j = 0; j < seqLength(); j++) {
					s += file.readChar();
				}
				int duplicates = file.readInt();
				elems.add(new Sequence(s, duplicates));
			}
			n = new Node(parent, children, elems);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	private long writeNode(Node n) {
		return -1;
	}
	
	
	private Long getRoot() {
		Long root = null;
		try {
			file.seek(ROOT_INDEX);
			root = file.readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
	
	private void setRoot(long r) {
		try {
			file.seek(ROOT_INDEX);
			file.writeLong(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Integer getDegree() {
		Integer degree = null;
		try {
			file.seek(DEGREE_INDEX);
			degree = file.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	
	private void setDegree(int d) {
		try {
			file.seek(DEGREE_INDEX);
			file.writeInt(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Long getEnd() {
		Long end = null;
		try {
			file.seek(END_INDEX);
			end = file.readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return end;
	}
	
	private void setEnd(Long end) {
		try {
			file.seek(DEGREE_INDEX);
			file.writeLong(end);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isRoot(long n) {
		return n == getRoot();
	}
	
	private Integer seqLength() {
		Integer length = null;
		try {
			file.seek(SEQ_LENGTH_INDEX);
			length = file.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return length;
	}
	
	private void setSeqLength(int length) {
		try {
			file.seek(SEQ_LENGTH_INDEX);
			file.writeInt(length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Long getParent(long index) {
		Long parent = null;
		try {
			file.seek(index + PARENT_OFFSET);
			parent = file.readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parent;
	}
	
	private void setParent(long index, long parent) {
		try {
			file.seek(index + PARENT_OFFSET);
			file.writeLong(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
}










//hello
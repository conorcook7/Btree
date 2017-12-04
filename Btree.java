import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BTree {

	private BTreeNode root, next;
	private int numNodes;
	private int tVar;  //t variable for equations
	private boolean cache; //boolean to use cache or not
	private RandomAccessFile write;
	
	

	public BTree(int t, boolean cache, int cacheSize) {
		tVar = t;
		//TODO
	}

	public int BtreeSearch(int x, int k){
		return 0;
		//TODO
	}
	
	public void BtreeSplitChild(int x, int i){
		//TODO
	}
	
	public void BtreeInsert(int T, int k){
		//TODO
	}
	
	public void BtreeInsertNonFull(int x, int k){
		//TODO
	}
	
	
	
	
	
	
	
	private class BTreeNode implements Comparable<BTreeNode> {
		private boolean leaf, root;
		private int maxKeys, parent, tVal, index;
		// references to the children nodes in a dynamic arraylist
		private ArrayList<Integer> childNodes;
		// all keys stored in a dynamic arraylist
		private ArrayList<treeObject> keyNodes;

		public BTreeNode(int tInput, int index, boolean root, boolean leaf) {
			this.tVal = tInput;
			this.leaf = leaf;
			this.root = root;
			this.index = index;

			if (this.root == true) {
				parent = -1;
			}

			maxKeys = (2 * tVal - 1);
			keyNodes = new ArrayList<treeObject>(2 * tVal - 1);
			childNodes = new ArrayList<Integer>(2 * tVal);

		}

		public boolean atMax() {
			if (keyNodes.size() == (2 * tVal - 1)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int compareTo(BTreeNode obj) { // from the comparable api
			int retVal = index - obj.index;
			return retVal;
		}
	}

	private class treeObject {
		private long key;
		private int num; // frequency

		public long getKey() {
			return key;
		}

		public treeObject(long input) {
			this.key = input;
		}

		public void duplicate() {
			num++;
		}

		public int compareTo(treeObject obj) { // from the comparable api

			if (this.key > obj.key) {
				return 1;
			} else if (this.key < obj.key) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
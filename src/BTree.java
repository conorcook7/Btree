import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

 /**
 * 
 * @author conor cook, zach garner, michael boyle
 *
 */
public class BTree {

	private BTreeNode root, nextNode, curNode;
	private int numNodes, seqL;
	private long sequence;
	private int tVal; // t variable for equations
	private RandomAccessFile write;

	/**
	 * Constructor for BTree
	 * 
	 * @param t
	 * @param seqLength
	 * @param seq
	 * @throws FileNotFoundException
	 */
	public BTree(int t, int seqLength/*, long seq*/) throws FileNotFoundException {
		seqL = seqLength;
		//sequence = seq;
		tVal = t;
		root = new BTreeNode(tVal, 0, true, true);
		numNodes = 1;
		write = new RandomAccessFile("tempFile", "rw");

		// check
	}

	public int BtreeSearch(BTreeNode rootNode, long key) {
		int i = 0;
		//while (i < )
//		i = 0
//			    while i < x.n and k > x.keyi
//			        i = i + 1
//			    if i < x.n and k == x.keyi
//			        return (x,i)
//			    elseif x.leaf
//			        return NIL
//			    else
//			        DISK-READ(x.ci)
//			        return B-Tree-Search(x.ci,k)
		return 0;
	}

	public void BtreeSplitChild(BTreeNode parent, BTreeNode split, int index) throws IOException {
		// TODO
		if (parent.atMax() || !split.atMax()) {
			throw new IllegalStateException();
		}
		BTreeNode newChild = new BTreeNode(tVal, numNodes, false, split.leaf);
		numNodes++;

		for (int i = split.keyNodes.size() - 1; i > tVal - 1; i--) {
			newChild.keyNodes.add(0, split.keyNodes.remove(i));
		}
		if (!split.leaf) {
			for (int i = split.childNodes.size() - 1; i >= tVal; i--) {
				newChild.childNodes.add(0, split.childNodes.remove(i));
			}
		}
		parent.childNodes.add(index + 1, newChild.index);
		newChild.parent = parent.index;
		parent.keyNodes.add(index, split.keyNodes.remove(tVal - 1));

		writeToFile(parent, false);
		writeToFile(split, false);
		writeToFile(newChild, false);
	}

	public void BtreeInsert(long kVal) throws IOException {
		if (root.atMax()) {
			nextNode = root;
			root = new BTreeNode(tVal, numNodes++, false, true);
			root.childNodes.add(0, nextNode.getIndex());
			nextNode.setParent(root.getIndex());
			nextNode.setRoot(false);
			BtreeSplitChild(root, nextNode, 0);
			BtreeInsertNonfull(new treeObject(kVal));
		} else {
			BtreeInsertNonfull(new treeObject(kVal));
		}
		
//		r = T.root
//				if r.n == 2t - 1
//			        s = Allocate-Node()
//			        T.root = s
//			        s.leaf = FALSE
//			        s.n = 0
//			        s.c0 = r
//			        B-Tree-Split-Child(s,0)
//			        B-Tree-Insert-Nonfull(s,k)
//			    else
//			        B-Tree-Insert-Nonfull(r,k)
	}
	
//	public void BTreeInsert(long subtree, treeObject key) {
//		
//	}
	private void BtreeInsertNonfull(treeObject key) throws IOException {
		// Please check
		boolean search = true;
		curNode = root;

		while (search) {
			int i = curNode.keyNodes.size() - 1;
			if (curNode.leaf) {
				while (i >= 0 && key.compareTo((treeObject) curNode.keyNodes.get(i)) <= 0) {
					if (key.compareTo((treeObject) curNode.keyNodes.get(i)) == 0) {
						// write to the file? for duplicates
						return;
					}
					i--;
				}
				curNode.keyNodes.add(i + 1, key);
				writeToFile(curNode, false);
				return;
			} else {
				while (i >= 0 && key.compareTo((treeObject) curNode.keyNodes.get(i)) <= 0) {
					if (key.compareTo((treeObject) curNode.keyNodes.get(i)) == 0) {
						// write to the file? for duplicates
						return;
					}
					i--;
				}
				i++;
				nextNode = readBTree((int) curNode.childNodes.get(i), tVal);

				if (nextNode.atMax()) {

					BtreeSplitChild(curNode, nextNode, i);
					if (key.compareTo((treeObject) curNode.keyNodes.get(i)) == 0) {
						// write to the file? for duplicates
						return;
					} else if (key.compareTo((treeObject) curNode.keyNodes.get(i)) > 0) {
						// read file
						nextNode = readBTree((int) curNode.childNodes.get(i + 1), tVal);
					}
				}
				curNode = nextNode;
			}
		}
	}

	public int size() {
		int metaData = 20;
		int keySize = 8 * (2 * tVal - 1);
		int childSize = 4 * (2 * tVal);
		int rVal = metaData + keySize + childSize;
		return rVal;
	}

	public BTreeNode readBTree(int index, int degree) throws IOException {
		// TODO
		BTreeNode newNode = new BTreeNode(1, index, false, false);
		if (newNode != null) {
			newNode.childNodes = newNode.childNodes;
			newNode.tVal = newNode.tVal;
			newNode.index = newNode.index;
			newNode.keyNodes = newNode.keyNodes;
			newNode.leaf = newNode.leaf;
			newNode.maxKeys = newNode.maxKeys;
			newNode.parent = newNode.parent;
			newNode.root = newNode.root;

			return newNode;
		}
		newNode = new BTreeNode(1, index, false, false);

		int keycount;
		newNode.tVal = degree;

		newNode.index = index;
		newNode.root = false;
		newNode.maxKeys = 2 * tVal - 1;

		ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + 4 + newNode.maxKeys * (8 + 4) + (newNode.maxKeys + 1) * (4));

		write.seek(12 + (size() * index));
		write.read(buffer.array());

		System.out.println(Arrays.toString(buffer.array()));

		newNode.parent = buffer.getInt();

		if (buffer.getInt() == 1) {
			newNode.leaf = true;
		} else {
			newNode.leaf = false;
		}
		keycount = buffer.getInt();
		newNode.keyNodes = new ArrayList<treeObject>(keycount);

		for (int k = 0; k < newNode.maxKeys; k++) {
			if (k < keycount) {
				newNode.keyNodes.add(k, new treeObject(buffer));

			} else {
				buffer.getLong();
			}
		}
		newNode.childNodes = new ArrayList<Integer>(keycount + 1);

		if (!newNode.leaf) {
			for (int c = 0; c < 2 * tVal; c++) {
				if (c < keycount + 1) {
					int temp = buffer.getInt();
					while (temp == -1) {
						temp = buffer.getInt();
					}
					newNode.childNodes.add(c, temp);
				} else {
					buffer.getInt();
				}
			}
		}
		BTreeNode returnNode = newNode;

		if (newNode != null)
			writeToFile(newNode, false);
		System.out.println(returnNode.childNodes);
		return returnNode;
	}

	public void writeToFile(BTreeNode node, boolean flag) throws IOException {
		// TODO
		if (node.root && !flag)
			return;
		ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + 4 + node.maxKeys * (8 + 4) + (node.maxKeys + 1) * (4));

		write.seek(12 + (size() * node.index));

		buffer.putInt(node.parent);
		buffer.putInt(node.leaf ? 1 : 0);
		buffer.putInt(node.keyNodes.size());

		for (int i = 0; i < node.maxKeys; i++) {
			if (i < node.keyNodes.size()) {
				buffer.putLong(((treeObject) node.keyNodes.get(i)).getKey());
			} else {
				buffer.putLong(-1);
			}
		}

		for (int i = 0; i < node.maxKeys + 1; i++) {
			if (i < node.childNodes.size()) {
				buffer.putInt((int) node.childNodes.get(i));
			} else {
				buffer.putInt(-1);
			}
		}
		write.write(buffer.array());

	}

	/**
	 * This is a Private class that creates and holds BTreeNodes. These nodes
	 * are placed and moved around within the Btree itself. Along with holding
	 * information, these nodes also implement the comparable interface so they
	 * can be compared to each other easily.
	 * 
	 * @author conor cook, zach garner, michael boyle
	 *
	 */
	private class BTreeNode implements Comparable<BTreeNode> {
		private boolean leaf, root;
		private int maxKeys, parent, tVal, index;
		// references to the children nodes in a dynamic arraylist
		private ArrayList<Integer> childNodes;
		// all keys stored in a dynamic arraylist
		private ArrayList<treeObject> keyNodes;

		/**
		 * This is the constructor of the BTreeNode Class Initializes variables,
		 * checks to see if it is the new root, creates the dynamic arrayLists
		 * to hold all of the nodes
		 * 
		 * @param tInput
		 * @param index
		 * @param root
		 * @param leaf
		 */
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

		/**
		 * This method is to get the index variable of the node if you are not
		 * within the Btree Class. Within this class you can simply access the
		 * information without this getter method.
		 * 
		 * @return index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * This method is to get the parent variable of the node if you are not
		 * within the Btree Class. Within this class you can simply access the
		 * information without this getter method.
		 * 
		 * @return parent
		 */
		public int getParent() {
			return parent;
		}

		/**
		 * This method is to set the parent variable of the node if you are not
		 * within the Btree Class. Within this class you can simply access the
		 * information without this setter method.
		 * 
		 * @param val
		 */
		public void setParent(int val) {
			parent = val;
		}

		/**
		 * This method is to set the root variable of the node if you are not
		 * within the Btree Class. Within this class you can simply access the
		 * information without this setter method.
		 * 
		 * @param val
		 */
		public void setRoot(boolean val) {
			root = val;
		}

		/**
		 * This method is to find out if the array holding the nodes is at its
		 * max or not. If at the max, then the dynamic array will increase the
		 * size to accommodate the new nodes
		 * 
		 * @return true/false
		 */
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

	/**
	 * This is a Private class that holds treeObjects that have a counter and a
	 * key associated with them. These are then placed into nodes which are used
	 * in the Btree.
	 * 
	 * @author conor cook, zach garner, michael boyle
	 *
	 */
	private class treeObject {
		private long key;
		private int num; // frequency

		/**
		 * Constructor of treeObjects Takes a long as the key that the object
		 * holds
		 * 
		 * @param input
		 */
		public treeObject(long input) {
			this.key = input;
		}

		/**
		 * Simple getter method for the key of the treeObject
		 * 
		 * @return key
		 */
		public long getKey() {
			return key;
		}

		/**
		 * This method is used if there is a need to keep track of the number of
		 * duplicate treeObjects there are in the Btree
		 */
		public void duplicate() {
			num++;
		}

		public treeObject(ByteBuffer buffer) throws IOException {
			key = buffer.getLong();
		}

		/**
		 * A compareTo method that is put in to satisfy the comparable interface
		 * that is implemented
		 * 
		 * @param obj
		 * @return 1/-1/0 depending on the circumstance
		 */
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
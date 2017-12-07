import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BTree {

	private BTreeNode root, nextNode, curNode;
	private int numNodes, seqL;
	private long sequence;
	private int tVal; // t variable for equations
	private RandomAccessFile write;

	public BTree(int t, int seqLength, long seq) throws FileNotFoundException {
		seqL = seqLength;
		sequence = seq;
		tVal = t;
		root = new BTreeNode(tVal, 0, true, true);
		numNodes = 1;
		write = new RandomAccessFile("Temp", "rw");

		// check
	}

	public int BtreeSearch(int x, int k) {
		return 0;
		// TODO
		//THIS METHOD IS USELESS APPARENTLY
	}

	public void BtreeSplitChild(BTreeNode parent, BTreeNode split, int index) {
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

	public void BtreeInsert(long kVal) {
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
	}

	private void BtreeInsertNonfull(treeObject key) {
		// Please check
		boolean searching = true;
		curNode = root;

		while (searching) {
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

	public BTreeNode readBTree(int index, int degree) {
		// TODO
		return curNode;

	}

	public void writeToFile(BTreeNode node, boolean flag) {
		// TODO

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

		public int getIndex() {
			return index;
		}

		public int getParent() {
			return parent;
		}

		public void setParent(int val) {
			parent = val;
		}

		public void setRoot(boolean val) {
			root = val;
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
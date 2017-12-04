import java.util.ArrayList;

public class BTree {

	BTreeNode root;

	public BTree(boolean cache, int cacheSize) {

	}

	private class BTreeNode implements Comparable<BTreeNode> {
		private boolean leaf, root;
		private int maxKeys, parent, tVal, index;
		private ArrayList<Integer> childNodes; // references to the children
													// nodes in a dynamic
													// arraylist
		private ArrayList<treeObject> keyNodes; // all keys stored in a dynamic
												// arraylist

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
public class BTree {

	BTreeNode root;
	
	
	public BTree(boolean cache, int cacheSize){
		
	}
	
	private class BTreeNode implements Comparable<BtreeNode>{
		private int n;			//number of keys stored in the node
		private int [] keys;	// references to keys stored in node
		private BTreeNode [] Children;	// reference to child nodes 
		private boolean leaf, root;
		private int maxKeys, parent, value, index;
		
		public BTreeNode( int value, int index, boolean root, boolean leaf){
			this.value = value;
			this.index = index;
			this.leaf = leaf;
			this.root = root;
			
			if(this.root == true){
				parent = -1;
			}
			
			maxKeys = (2*value -1);
			
		}
		
		public boolean atMax(){
		}
		
		public int compareTo(BtreeNode obj){ //from the comparable api
			int retVal = index-obj.index
			return retVal;
		}
	}
	
	private class treeObject{
		private long key;
		private int num; //frequency
		
		public long getKey(){
			return key;
		}
		
		public treeObject(long input){
			this.key = input;
		}
		
		public void duplicate(){
			num++;
		}
		
		public int compareTo(treeObject obj){ //from the comparable api
			if(this.key > obj.key){
				return 1;
			}else if(this.key < obj.key){
				return -1;
			}else{
				return 0;
			}
		}
	}
	
}
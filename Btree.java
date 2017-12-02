public class BTree {

	BTreeNode root;
	
	
	public BTree(boolean cache, int cacheSize){
		
	}
	
	public class BTreeNode{
		private int n;			//number of keys stored in the node
		private int [] keys;	// references to keys stored in node
		private BTreeNode [] Children;	// reference to child nodes 
		private boolean leaf, root;
		private int maxKeys, parent, value;
		
		public BTreeNode( int value, BTreeNode parent, int maxKeys, int maxChildrenSize ){
			this.value = value;
			
		}
		
	}
	
}
public class BTree {

	BTreeNode root;
	
	
	public BTree(){
		
	}
	
	public class BTreeNode{
		private int n;			//number of keys stored in the node
		private int [] keys;	// references to keys stored in node
		private boolean leaf;
		private BTreeNode [] Children;	// reference to child nodes 
		
		public BTreeNode( BTreeNode parent, int maxKeysize, int maxChildrenSize ){
			
			
		}
		
	}
	
}
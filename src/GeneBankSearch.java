

public class GeneBankSearch {

	public static void main(String[] args) {
		//java GeneBankSearch  <btree file> <query file> [<debug level>]
		
		if(args.length != 3) {
			printUsage();
			return;
		}
		
		if(Integer.parseInt(args[2]) != 0 || Integer.parseInt(args[2]) != 1) {
			printUsage();
			return;
		}
		
		
		
		
	}

	
	static String binName(String title, int sequence, int degree) {
		return title + ".gbk.btree.data" + sequence + degree;
	}
	
	static void printUsage() {
		System.out.println("Usage: GeneBankSearch <btree file> <query file> [0/1<debug level>]");
	}
}

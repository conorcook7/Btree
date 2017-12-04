
public class GeneBankSearch {

	public static void main(String[] args) {
		if (!((args.length >= 3 && args.length <= 5) && (args[0] == 0 || args[0] == 1))) {
			printUsage();
			return;
		}
		if (args.length >= 4 && !(args[3] > 0)) {
			printUsage();
			return;
		}
		if (args.length >= 5 && args[4] != 0) {
			printUsage();
			return;
		}
		
		
		
		

	}

	
	static string binName(string title, int sequence, int degree) {
		return title + ".gbk.btree.data" + sequence + degree;
	}
	
	static void printUsage() {
		System.out.println("Usage: GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>] [<debug level>]");
	}
}

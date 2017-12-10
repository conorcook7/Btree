import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneBankSearch {

	public static void main(String[] args) throws FileNotFoundException {
		//java GeneBankSearch  <btree file> <query file> [<debug level>]
		
		if(args.length != 3) {
			printUsage();
			return;
		}
		
		if(Integer.parseInt(args[2]) != 0 && Integer.parseInt(args[2]) != 1) {
			printUsage();
			return;
		}
		String btree = args[0];
		String query = args[1];
		File queryFile = new File(query);
		TestTree myTree = new TestTree(btree);
		
		Scanner scanQ = new Scanner(queryFile);
		
		while(scanQ.hasNextLine()) {
			System.out.println(myTree.search(scanQ.nextLine()));
		}
		
		
	}

	
	static String binName(String title, int sequence, int degree) {
		return title + ".gbk.btree.data" + sequence + degree;
	}
	
	static void printUsage() {
		System.out.println("Usage: GeneBankSearch <btree file> <query file> [0/1<debug level>]");
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author conor cook, zach garner, michael boyle
 *
 */
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
		
		while (scanQ.hasNextLine()) {
		    String next = scanQ.nextLine();
		    Sequence result = myTree.search(next);
		    if (result == null) {
		        System.out.println(next + ": 0");
		    }else {
		    	System.out.println(result);
		    }
		}
	}

	
	static String binName(String title, int sequence, int degree) {
		return title + ".gbk.btree.data" + sequence + degree;
	}
	
	static void printUsage() {
		System.out.println("Usage: GeneBankSearch <btree file> <query file> [0/1<debug level>]");
	}
}

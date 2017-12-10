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
		
		String treePath;
		String querPath;
		boolean debug;
		
		try {
			treePath = args[0];
			querPath = args[1];
			if (args.length > 2)
				debug = args[2] == "1";
		} catch(Exception e) {
			printUsage();
			return;
		}
		
		TestTree myTree = new TestTree(treePath);
		File queryFile = new File(querPath);
		
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

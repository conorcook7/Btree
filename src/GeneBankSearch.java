import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is one of the main classes we have for the project. This class is mainly
 * a command line utility for searching for information about a sequence in a
 * btree of information formatted by GeneBankCreateBTree.
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
		} catch (Exception e) {
			printUsage();
			return;
		}
		TestTree myTree = new TestTree(treePath);
		File queryFile = new File(querPath);
<<<<<<< HEAD
		Scanner scanQ = new Scanner(queryFile);		
		
=======
		Scanner scanQ = new Scanner(queryFile);

>>>>>>> branch 'master' of https://github.com/Michael-Boyle/proj4
		while (scanQ.hasNextLine()) {
			String next = scanQ.nextLine();
			Sequence result = myTree.search(next);
			if (result == null) {
				System.out.println(next + ": 0");
			} else {
				System.out.println(result);
			}
		}
	}

	/**
	 * This method is to create the name of the btree binary file and return that
	 * string name
	 * 
	 * @param title
	 * @param sequence
	 * @param degree
	 * @return title + ".gbk.btree.data" + sequence + degree
	 */
	static String binName(String title, int sequence, int degree) {
		return title + ".gbk.btree.data" + sequence + degree;
	}

	/**
	 * Simple method to create our own version of printUsage
	 * ------------------------------------------------------ Contains the correct
	 * format for the input of this class
	 */
	static void printUsage() {
		System.out.println("Usage: GeneBankSearch <btree file> <query file> [0/1<debug level>]");
	}
}

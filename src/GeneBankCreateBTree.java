import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * This is the primary class to the BTree project.
 * 
 * This class is a command line utility to create a BTree of nodes holding DNA
 * sequences. This is done by using the provided gbk.files. This is all written
 * and read from disk or from a binary file
 * 
 * 
 * @author Zachary Garner, conor cook, michael boyle
 */
public class GeneBankCreateBTree {

	public static void main(String[] args) {

		int degree = 0;
		String gbkFile = null;
		int seqLength = 0;
		boolean debugLvl = false;

		try {
			degree = Integer.parseInt(args[0]);
			gbkFile = args[1];
			seqLength = Integer.parseInt(args[2]);
			if (args.length == 4)
				debugLvl = args[3] == "1";
			parseFile(degree, gbkFile, seqLength, debugLvl);
		} catch (FileAlreadyExistsException e) {
			System.out.println("bin associated with gbk already exists");
		} catch (Exception e) {
			printUsage();
			e.printStackTrace();
			return;
		}
		System.out.println("done");
	}

	/**
	 * This method parses the gbk.file that is given, searches for the word, ORIGIN,
	 * and then begins logging the sequences that follow within that file
	 * 
	 * @param degree
	 * @param file
	 * @param seqLength
	 * @param debugLvl
	 * @throws FileAlreadyExistsException
	 */
	public static void parseFile(int degree, String file, int seqLength, boolean debugLvl)
			throws FileAlreadyExistsException {

		String binName = file + ".btree.data." + seqLength + "." + degree;
		File gbkFile = new File(file);

		TestTree tree = null;
		tree = new TestTree(binName, degree, seqLength);

		Scanner scanFile = null;
		try {
			scanFile = new Scanner(gbkFile);
		} catch (FileNotFoundException e) {
			System.out.println("gbk file not found");
			e.printStackTrace();
		}

		StringBuilder SB;
		Scanner otherScanner;

		String curVal = scanFile.next();

		while (scanFile.hasNext()) {
			if (curVal.equals("ORIGIN")) { // finds the key origin indicating the beginning of the dna sequence
				SB = new StringBuilder();

				while (!curVal.equals("//") && !curVal.equals("N")) {
					scanFile.next(); // removes the in at the beginning of each line in the sequence

					SB.append(scanFile.nextLine()); // adds line to the string representing the DNA sequence
					otherScanner = scanFile;
					curVal = otherScanner.next();

				}
				String dnaSeq = new String(SB);
				dnaSeq = dnaSeq.replaceAll("\\s+", ""); // removes spaces from the string that represents a DNA
														// sequence
				parseToSubSequence(dnaSeq, seqLength, tree);
			}
			if (scanFile.hasNext())
				curVal = scanFile.next();
		}

		scanFile.close();
	}

	/**
	 * This is a helper method to parseFile() -------------------------------------
	 * Contains the functionality to store the sequence into the btree
	 * 
	 * @param seq
	 * @param degree
	 * @param tree
	 */
	public static void parseToSubSequence(String seq, int seqLength, TestTree tree) {

		StringBuilder SB = new StringBuilder();

		for (int i = 0; i < seq.length() - seqLength; i++) {
			for (int j = i; j < i + seqLength; j++) {
				SB.append(seq.charAt(j));
			}
			tree.insert(SB.toString());
			SB.setLength(0);
		}
	}

	/**
	 * Simple method to create our own version of printUsage
	 * ------------------------------------------------------ Contains the correct
	 * format for the input of this class
	 */
	static void printUsage() {
		System.out.println("Usage: GeneBankCreatBTree <degree> <gbk file> <sequence length> [0/1<debug level>]");
	}

}

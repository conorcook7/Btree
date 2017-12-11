import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author Zachary Garner
 */
public class GeneBankCreateBTree {

	/**
	 * 
	 */
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

	}


	/**
	 * @param cache
	 * @param degree
	 * @param file
	 * @param seqLength
	 * @param cacheSize
	 * @param debugLvl
	 * @throws FileAlreadyExistsException 
	 */
	public static void parseFile(int degree, String file, int seqLength, boolean debugLvl) throws FileAlreadyExistsException {

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
				parseToSubSequence(dnaSeq, degree, tree);
			}
			if (scanFile.hasNext())
				curVal = scanFile.next();
		}

		scanFile.close();
	}

	/**
	 * @param seq
	 * @param seqLength
	 * 
	 */
	public static void parseToSubSequence(String seq, int degree, TestTree tree) {

		StringBuilder SB = new StringBuilder();

		for (int i = 0; i < seq.length(); i++) {
			for (int j = i; j < i + seq.length(); j++) {
				SB.append(seq.charAt(j));
			}
			tree.insert(SB.toString());
			SB.setLength(0);
		}
	}

	static void printUsage() {
		System.out.println("Usage: GeneBankCreatBTree <degree> <gbk file> <sequence length> [0/1<debug level>]");
	}

}

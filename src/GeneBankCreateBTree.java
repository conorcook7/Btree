import java.util.*;
import java.io.*;

/**
 * @author Zachary Garner
 */
public class GeneBankCreateBTree {
	
/**
 * 
 */
	public static void main(String[] args){
		
		if( args.length < 3 || args.length > 5){
			System.out.println("Input should be in the following format:" + "\n" +
					" <degree> <gbk file> <sequence length> [<cache size>] [<debug level>]"
					+ "\n The sequence length needs to be beween 0 and 31.");
			System.exit(1);
					
		}
		try{
			parseCommandLineArgs(args);
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Checks command line arguments and prints a message if the arguments are not correct.
	 */
	public static void parseCommandLineArgs(String[] args)throws Exception{
		
		int degree = 0;
		File myFile = null;
		int seqLength = 0;
		int cacheSize = 0;
		int debugLvl = 0;
		
		for (int i = 0; i < args.length; i++){
			switch(i){
			
			case 0: 
				try{
					degree = Integer.parseInt(args[i]);
				}catch(NumberFormatException e){
					e.printStackTrace();
					System.exit(1);
				}	
			break;
			
			case 1:
				try{
					myFile = new File(args[i]);
					if( ! myFile.exists() ) throw new FileNotFoundException("The File " + args[i] + " does not exist."); 
				}
				catch(FileNotFoundException e){
					e.printStackTrace();
				}
			break;
			
			case 2:
				try{
					seqLength = Integer.parseInt(args[i]);
					if(seqLength > 31 || seqLength < 0){
						System.out.println("The sequence length needs to be between 0 and 31.\n");
						System.exit(1);
						}
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			break;
			
			case 3: 
				try{
					cacheSize = Integer.parseInt(args[i]);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			break;
			
			case 4:
				try{
					debugLvl = Integer.parseInt(args[i]);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}	
				if(debugLvl == 0){
					System.out.println("no debug");
				}else if(debugLvl == 1){
					System.out.println("with debug");
				}else{
					System.out.println("arg 1 incompatible");
					System.exit(1);
					}
				break;
			}
			
		}
		
		System.out.println("end of parse");
		
		parseFile( degree, myFile, seqLength, cacheSize, debugLvl);
		
	}

	/**
	 * @param cache
	 * @param degree
	 * @param file 
	 * @param seqLength
	 * @param cacheSize
	 * @param debugLvl
	 */
	public static void parseFile( int degree, File file, int seqLength, int cacheSize, int debugLvl)throws Exception{
		
		
		TestTree tree = new TestTree("thefilewewillsaveto.txt", degree, seqLength);

		
		
		try{
		Scanner scanFile = new Scanner(file);
		StringBuilder SB;
		Scanner otherScanner;
		
		String curVal = scanFile.next();
		
		while (scanFile.hasNext()){
			if( curVal.equals("ORIGIN") ) { 	//finds the key origin indicating the beginning of the dna sequence 
				System.out.println("Origin found");
				SB = new StringBuilder();
				
				while(!curVal.equals("//") && !curVal.equals("N")){
					scanFile.next(); 	// removes the in at the beginning of each line in the sequence
					
					SB.append(scanFile.nextLine()); // adds line to the string representing the DNA sequence
					otherScanner = scanFile;
					curVal = otherScanner.next();
					
				}
				String dnaSeq = new String(SB);
				dnaSeq = dnaSeq.replaceAll("\\s+",""); // removes spaces from the string that represents a DNA sequence
				parseToSubSequence(dnaSeq, seqLength, degree, tree);
			}
			if(scanFile.hasNext()) curVal = scanFile.next();
		}
		
		
		scanFile.close();
		}catch(FileNotFoundException e){
		}
	}
	
	
	/**
	 * @param seq
	 * @param seqLength
	 * 
	 */
	public static void parseToSubSequence(String seq, int seqLength, int degree, TestTree tree){
		
		StringBuilder SB = new StringBuilder();
		
		for(int i = 0; i < seq.length() - seqLength; i++ ){
			for(int j = i; j < i + seqLength; j++ ){
				SB.append(seq.charAt(j));
			}
			System.out.println(SB.toString());
			tree.insert(SB.toString());
			SB.setLength(0);
		}
		
	}

}

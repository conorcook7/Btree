# Btree
****************
* Team Programming Project: Bioinformatics (BTree)
* Matthew Thomas CS321
* Data Structures, Fall 2017
* Conor Cook , Zach Garner, Michael Boyle
**************** 

OVERVIEW:

 This project is to represent the importance of a BTree data
 structure. We utilize a BTree structure by storing certain 
 sequences of a DNA string, into nodes that are then placed
 into this BTree structure. After that we write to disk and 
 can view the information about each said sequence that you
 are searching for. 

INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 e.g. 
 * Tuple.java - source file
 * Sequence.java - source file
 * BTree.java - source file 
 * GeneBankCreateBTree.java - source file
 * GeneBankSearch.java - source file
 * README - this file


COMPILING AND RUNNING:
 
 From the directory containing all source files, compile the
 driver class (and all dependencies) with the commands:
 $ javac Tuple.java
 $ javac Sequence.java
 $ javac BTree.java
 $ javac GeneBankCreateBTree.java
 $ javac GeneBankSearch.java

 Run the compiled classes file with the command:
 $ java GeneBankCreateBTree.java
 $ java GeneBankSearch.java
 
 When you are given the command to run the program, you will
 be given command-line arguments to enter. The two classes you 
 can run have different parameters to be run. The formats are below
 
 Format for GeneBankCreateBTree: 
 <degree> <gbk file> <sequence length> [0/1<debug level>]
 
 Format for GeneBankSearch:
 <btree file> <query file> [0/1<debug level>]
 
 Console output will give the results after the programs finish.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 Tuple:
 We made this class to be a convenience class to return multiple 
 variables at a time, this was very useful.
 
 Sequence:
 This class we made as an object class for the sequences themselves
 and these sequences hold the String of the sequence that it is, and 
 also holds the amount of duplicates for each sequence.
 
 BTree:
 This is the meat of the program in a sense where this is where all 
 of the functions regarding the functionality of a BTree resides. 
 We have multiple helper functions in order to help debugging and 
 readability/more robust code. 
 
 Layout of the B-Tree on file on disk:
 The main layout starts with a 4 byte integer giving the degree of the
 BTree, then a 4 byte integer giving the sequence length of the BTree,
 then an 8 byte long giving the index of the root node, then an 8 byte
 long denoting the index of the next open index/end, then each node 
 contains an 8 byte long denoting the index of its parent, a 4 byte 
 integer indicating the amount of children a node has, then each
 child is represented by 8 byte long that is its index in the BTree.
 Space is allocated for the maximum number of children, after the 
 children each element is denoted by a number of 2 byte chars 
 determined by the sequence length of the BTree, followed immediately 
 by a 4 byte integer representing the number of duplicates of the 
 element. Space is then allocated for the maximum possible number of 
 elements.
 
 GeneBankCreateBTree:
 This class is made to create the binary file that is later on read
 by GeneBankSearch. This class houses all of the parsing and handling
 of creating the BTree.
 
 GeneBankSearch:
 This class is simply responsible for looking at a query file and the 
 gbk.file and searching the gbk.file for how many times the sequences
 are duplicated, and then prints the number of duplicates for each
 said sequence that is in the query file.

TESTING:

 We tested the classes first, by using the simple PrintLine checks
 to correct any easy changes and catch an errors that we could 
 simply fix. The other testing is that we handled the command line
 arguments in the sense that if you pass bad information then should
 safely exit and give the correct printUsage. By no means is this idiot 
 proof as it has not been exhaustively tested due to time-constraints.
 
 Unfortunately we were not able to finish and could not finish testing
 all of the the bugs. This was very frustrating turning in a non-finished 
 project but please read the code and if you can find our error then please
 email one of us.
 
 Sitting right now, the BTree creates just fine but is not populating 
 the tree correctly with the sequences so the program prints with the 
 incorrect values, currently with zeros for all the sequences found.
 
 
DISCUSSION:
 
 This project has definitely interesting for our entire team. 
 Throughout the duration of this project we have battled some serious 
 issues and have learned many things in the process. To start this 
 charade off, our first opponent was GIT, and using GITHUB. We decided
 that moving our project into the GIT world would bear fruit in the
 end. After countless hours figuring that out, we managed to master
 GIT, with random corruptions, crashes, banned accounts, etc. 
 Although it was difficult at times dealing with GIT,  it managed to 
 be of quite use with the version control system that we could 
 constantly be pushing back and forth to each other.
 
 The next idea we had was to use a tuple since Java doesn't naturally 
 provide such a class. We ended up making our own to use. This just 
 made sense to us to return the two types of information for convenience.
 This actually ended up broadening our conceptions about tuples, 
 while at the same time, giving our code a robust look. We never tried
 it before so why not go all out on the final project!
 
 Creating the BTree was very challenging for us since we did the whole
 class twice. We started with a more abstract version with the psuedocode
 and that didn't work for us as well so we made our version that we 
 ended up debugging for hours as well anyways. Making our own version 
 gave us more of an understanding how the structure works in our 
 opinion.
 
 Don't forget about parsing the file! This little gold mine of fun
 was very interesting for us as the format had to skip everything in the
 file except the sequence data which was very intriguing. Took some time
 but we got the problems handled and turned out to work fairly well.
 
 Above all of the other problems we have encountered, creating an 
 interface to the binary file was by far the hardest part about this project. 
 We could have finished this so much faster if there wasn't any storage
 onto a binary file(but that would defeat the purpose of this class:) ).
 Having this being our biggest boundary we had to spend a lot of time 
 researching the topics involving RandomAccessFiles in Java and how to
 correctly use them, allocate for them and even how to read them. This 
 was very difficult especially with never of done anything even close to
 this.

 This project proved to be a difficult yet rewarding project. Even though
 through all of the complaints, we have found many positives. For example, 
 real life group work in a computer science realm and how this relates to
 real life jobs. You have to communicate heavily with your teammates to get
 the project where it needs to go. You need to have some confidence in your
 teammates as well. You need to be diligent with your end of the bargain 
 of shared work. These are just the tip of the proverbial iceberg. We have
 learned these lessons and plan to grow these as we move on with our careers.
 

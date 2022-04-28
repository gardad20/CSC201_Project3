// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
//
// Abby Gardner and Sophie Lee

import java.io.File;
import java.io.IOException;

public class DNAdbase {

    private static int hashSize;
    private static File command;
    private static String memory;
    private static DNAparser parse;

    /**
     * 
     * @param args is the input
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // check command line arguments
        if (args.length == 3) {
            command = new File(args[0]); // command file
            hashSize = Integer.parseInt(args[1]); // hash table size (must be factor of 32)
            memory = args[2]; // memory file

            parse = new DNAparser(command, memory, hashSize); // run DNA parser
            parse.parse();
        }
        else {
            System.out.println("Please input a correctly formatted command: ");
            System.out.println("java DNAdbase <command-file> <hash-table-size> <memory-file>");
        }
    }
    
    /**
     * 
     * @return the parser
     */
    public DNAparser getParser() {
        return parse;
    }
    
}

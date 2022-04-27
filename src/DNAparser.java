import java.io.*;
import java.util.Scanner;

/**
 * 
 * @author Abby Gardner and Sophie Lee
 * @version 
 */
public class DNAparser {

    private File commandFile;
    private MemoryManager memory;

    /**
     * 
     * @param c file
     * @param m memory
     * @param size size
     * @throws IOException will throw
     */
    public DNAparser(File c, String m, int size) throws IOException {
        commandFile = c;
        memory = new MemoryManager(m, size);
    }
    
    /**
     * 
     * @return the memory manager
     */
    public MemoryManager getMemory() {
        return memory;
    }

    /**
     * Parses the file
     * 
     * @return if the parsing is complete
     * @throws IOException 
     */
    public boolean parse() throws IOException {

        // parses the info in the commandFile
        // calls insert, search, etc. in MemoryManager (which will automatically call the same function in the hash)

        try {
            Scanner scnr = new Scanner(commandFile); //opens scanner to read the whole input file from the command line

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine().trim(); //removes end (and multiple) whitespaces from line
//                Scanner linescnr = new Scanner(new BufferedReader(new FileReader(line)));  //scanner to read each line of scnr's input

                //String command = linescnr.next().toLowerCase(); //gets the command (either insert, remove, search, print)
                String[] command = line.split("\\s+"); //splits the line by spaces

                if (command[0].equals("insert")) {
                    String seqID = command[1];
                    System.out.println(seqID);
                    int seqLen = Integer.parseInt(command[2]);
                    String seq = scnr.nextLine();//.trim().replaceAll("\\s", "")
                    HashObject hashed = memory.insert(seqID, seq, seqLen);
                    if (hashed == null) {
                        HashObject searched = memory.searchHash(command[1]);
                        if (searched.getSkip() == 32) {
                            System.out.println("Bucket full.Sequence " +
                                    command[1] + " could not be inserted");
                        }
                        else {
                            System.out.println("SequenceID " +
                                    command[1] + " exists");
                        }
                    }

                } else if (command[0].equals("remove")) {
                    String seqID = command[1];
                    memory.remove(seqID);
                } else if (command[0].equals("search")) {
                    String seqID = command[1];
                    System.out.println(memory.search(seqID));
                } else if (command[0].equals("print")) {
                    memory.print();
                } else { //this would happen when the line is null and doesn't have any commands
                    continue;
                }

//            System.out.println("command: "+ command);

            }
        }
        catch(EOFException e){
            return true; //at the end of the file, stop parsing
        }

        return true;
    }
}

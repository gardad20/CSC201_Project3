import java.io.BufferedReader;
import  java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        boolean doneParsing = false;  //Why is this needed? why should this return a boolean?

        Scanner scnr = new Scanner(new BufferedReader(new FileReader(commandFile))); //opens scanner to read the whole input file from the command line
        String line = "";

        while (scnr.hasNextLine()) {
            line = scnr.nextLine().trim().replaceAll("\\s", ""); //removes end (and multiple) whitespaces from line
            Scanner linescnr = new Scanner(new BufferedReader(new FileReader(line)));  //scanner to read each line of scnr's input

            String command = linescnr.next().toLowerCase(); //gets the command (either insert, remove, search, print)

            if (command.equals("insert")){
                String seqID = linescnr.next();
                int seqLen = linescnr.nextInt();
                String seq = scnr.nextLine().trim().replaceAll("\\s", "");

                memory.insert(seqID, seq, seqLen);
            }
            else if (command.equals("remove")){
                String seqID = linescnr.next();
                memory.remove(seqID);
            }
            else if (command.equals("search")){
                String seqID = linescnr.next();
                memory.search(seqID);
            }
            else if (command.equals("print")){
                memory.print();
            }
            else { //this would happen when the line is null and doesn't have any commands
                continue;
            }

//            System.out.println("command: "+ command);

        }



        doneParsing = true;
        return doneParsing;


    }
}

import  java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author 
 * @version 
 */
public class DNAparser {

    private File com;
    private MemoryManager memory;

    /**
     * 
     * @param c file
     * @param m memory
     * @param size size
     * @throws IOException will throw
     */
    public DNAparser(File c, String m, int size) throws IOException {
        com = c;
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















    }
}

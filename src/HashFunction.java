import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashFunction implements HashTable<String, HashObject> {
    private HashObject[] table;
    private int hashsize;

    // class constructor to create a hashtable of the specified size
    public HashFunction(int hashsize){
        table = new HashObject[hashsize];
        this.hashsize = hashsize;
    }

    // inserts the specified key (& corresponding value) into the hashtable
    public Integer insert(String key, HashObject value) {
        int index = (int)sfold(key, table.length); //find where the id hashes to
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        for (int i = 0; i < 32; i++) {
            if (table[spot] == null || table[spot].getTombstone()) {
                table[spot] = value;
                break;
            }
            else if ((spot + 1)% 32 == 0) {
                //will loop to the top of the bucket to continue searching for a place to insert
                spot = reset;
            }else {
                spot++;
            }
        }
        return -1;
    }


    // this method removes the sequence associated with the sequenceID from the hashtable
    public HashObject remove(String id, Integer amountToSkip){
        int index = (int)(sfold(id, table.length)); //find where the id hashes to
        int block = index / 32;
        int reset = block * 32;
        int current = (index + amountToSkip) % 32;
        current = current + reset;
        HashObject hashObj = table[current]; //inserting the id into the correct spot
        hashObj.setTombstone(true);
        return null;
    }

    // finds and returns the sequence in hashtable corresponding to the sequenceID if it exists
    public HashObject search(String id, Integer amountToSkip){
        int index = (int)(sfold(id, table.length)); //find where the id hashes to
        int block = index / 32;
        int reset = block * 32;
        int current = (index + amountToSkip) % 32;
        current = current + reset;
        HashObject hashObj = table[current];
        if(hashObj != null && hashObj.getTombstone()){
            return null;
        }
        return hashObj;
    }

    // returns an array of hashObjects in the hashtable
    public HashObject[] print(){
        return table;
    }

    /**
     * 
     * @param s
     *            is the string to be hashed
     * @param m
     *            hash table size
     * @return the hash value
     */
    private long sfold(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return (Math.abs(sum) % m);
    }
}

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashFunction implements HashTable<String, HashObject> {
    private HashObject[] table;
    private int hashsize;

    public HashFunction(int hashsize){
        table = new HashObject[hashsize];
        this.hashsize = hashsize;
    }

    public Integer insert(String key, HashObject value) {
        int index = (int)sfold(key, table.length);
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        for (int i = 0; i < 32; i++) {
            if (table[spot] == null || table[spot].getTombstone()) {
                table[spot] = value;
                break;
            }
            else if ((spot + 1)% 32 == 0) {
                spot = reset;
            }else {
                spot++;
            }
        }
        return -1;
    }


    public HashObject remove(String id, Integer amountToSkip){
        int index = (int)(sfold(id, table.length)); //find where the id hashes to
        int count = amountToSkip;
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        int current = (spot + amountToSkip) % 32;
        current = current + reset;
        HashObject hashObj = table[current];
        hashObj.setTombstone(true);
        return null;
    }

    public HashObject search(String id, Integer amountToSkip){
        int index = (int)(sfold(id, table.length)); //find where the id hashes to
        int count = amountToSkip;
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        int current = (spot + amountToSkip) % 32;
        current = current + reset;
        HashObject hashObj = table[current];
        return hashObj;
    }

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

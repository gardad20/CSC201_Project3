import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashFunction implements HashTable<String, HashObject> {
    private class Entry<String, HashObject> implements Map.Entry<String, HashObject> {

        String key;
        HashObject value;

        Entry(String key, HashObject value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public HashObject getValue() {
            return value;
        }

        @Override
        public HashObject setValue(HashObject value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private Entry<String, HashObject>[] table;
    private int size;

    public HashFunction(int hashsize){
        table = new Entry[hashsize];
    }

    public void insert(String key, HashObject value) {
        for (int i = 0; i < table.length; i++) {

            long index = sfold(key, i);
            if (table[index] == null) {
                table[index] = new Entry(key, value);
                size++;
                return null;
            } else if (key.equals(table[index].key)) {
                HashObject ret = table[index].value;
                table[index].value = value;
                return ret;
            }

        }
    }

    public void remove(String, int){

    }
    public HashObject search(String, int){

    }

    public HashObject[] print(){

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

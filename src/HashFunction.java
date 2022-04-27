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

    private HashObject[] table;
    private int hashsize;
    private int size;

    public HashFunction(int hashsize){
        table = new HashObject[hashsize];
        this.hashsize = hashsize;
    }

    public void insert(String key, HashObject value) {
        // David looked at this whole method and OKAYed it
        int index = (int)sfold(key, table.length);
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        for (int i = 0; i < 32; i++) {
            if (table[spot] == null || table[spot].getTombstone()) {
                table[spot] = value;
                break;
            }
            else if ((spot + 1)% 32 == 0)
                spot = reset;
            else
                spot++;
        }
    }


    public HashObject remove(String id, Integer amountToSkip){
        // David looked at this whole method and OKAYed it
        int index = (int)(sfold(id, table.length)); //find where the id hashes to
        int count = amountToSkip;
        int block = index / 32;
        int reset = block * 32;
        int spot = index;
        int current = (spot + amountToSkip) % 32;
        current = current + reset;
        HashObject hashObj = table[spot];
        hashObj.setTombstone(true);
//        for(int i=firstSlot; i<table.length; i++){
//            HashObject hashObj = table[spot];
//            if(table[spot].getKey().equals(id)){
//                table[spot].getValue().setTombstone(true);
//            }
//            else if(((spot + 1) % 32 == 0)){ // will loop back to the beginning of the bucket
//                spot = 0;
//            }
//            else {
//                spot ++;
//            }
//            if (hashObj == null){ return null;}
//            if (hashObj.getTombstone()) { continue;}
//            if (count == 0){
//                if (hashObj == null) { return null;}
//                hashObj.setTombstone(true);
//                return hashObj;
//            }
//        }
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
        HashObject hashObj = table[spot];
        return hashObj;
//        int firstSlot = (int)(sfold(id, table.length)); //find where the id hashes to
//        int spot = firstSlot;
//
//        for (int i = firstSlot; i < table.length; i++){ //starts at the hash slot, then loops to the end of the array to find the hashObject
//            if (table[spot].key.equals(id) || table[spot].getValue().getTombstone()) { // a hashObject with that id WAS found
//                return table[spot].value;
//            }
//            else if ((spot + 1) % 32 == 0){ // will loop back to the beginning of the bucket
//                spot = 0;
//            }
//        }
//        return null; // else: a hashObject with that id was NOT found
    }

    public HashObject[] print(){
        return table;
//        HashObject[] printArr = new HashObject[hashsize];
//        int counter = 0;
//        for(int i=0; i<table.length; i++){
//             //is there is something in the slot, then print it out
//            if (table[i] == null || table[i].getValue().getTombstone()) {
//                continue;
//            }
//                printArr[counter] = table[i].getValue();
//                counter++;
//        }
//        return printArr;
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

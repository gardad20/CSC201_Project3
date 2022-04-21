import java.util.Hashtable;

/**
 * 
 * @author 
 * @version 
 * @param <T> is a comparator
 * @param <K> is a comparator
 */
public interface HashTable<T extends Comparable<T>, K> {

    //T is String, K is hashObject

    public void sfold();
    public void insert(T,K);
    public void remove(T, int);
    public K search(T, int);
    public K[] print();

}

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

    //public void sfold(T t, int num);
    public void insert(T t,K k);
    public HashObject remove(T t, Integer amountToSkip);
    public K search(T t, Integer amountToSkip);
    public K[] print();
}

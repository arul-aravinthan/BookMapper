// --== CS400 Project One File Header ==--
// Name: Arul Aravinthan
// CSL Username: arul
// Email: aravinthan@wisc.edu
// Lecture 003 @2:25 p.m
// Notes to Grader: None

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/** My implementation of HashtableMap
 * @author Arul Aravinthan
 */
public class HashtableMap<KeyType, ValueType> implements IterableMapADT<KeyType,ValueType> {
    protected LinkedList<Pair<KeyType, ValueType>>[] arr;
    //Number of elements in hashTableMap
    private int size = 0;
    /**
     * Initializes array with user inputted capacity
     * @param capacity capacity to set the hashtableMap to
     */
    @SuppressWarnings("unchecked")
    public HashtableMap(int capacity) {
        arr = new LinkedList[capacity];
    }
    /**
     * Initializes array with default capacity 15
     */
    @SuppressWarnings("unchecked")
    public HashtableMap() {
        arr = new LinkedList[15];
    }


    /**
     * Inserts a new (key, value) pair into the hashtableMap if the hashtableMap does not
     * contain a value mapped to key yet.
     *
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will hashtableMap to
     * @return true if the (key, value) pair was inserted into the hashtableMap,
     * false if a mapping for key already exists and the
     * new (key, value) pair could not be inserted
     */
    public boolean put(KeyType key, ValueType value) {
        boolean ret;
        if(containsKey(key)) {
            ret = false;
        } else {
            Pair<KeyType, ValueType> newPair = new Pair<>(key, value);
            if(arr[Math.abs(key.hashCode()) % arr.length] == null) {
                LinkedList<Pair<KeyType, ValueType>> bucket = new LinkedList<>();
                bucket.add(newPair);
                arr[Math.abs(key.hashCode()) % arr.length] = bucket;
            } else {
                arr[Math.abs(key.hashCode()) % arr.length].add(newPair);
            }
            size++;
            ret =  true;
        }
        if(size() / (double) arr.length >= 0.7) {
            reHash();
        }
            return ret;
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the hashtableMap does not contain a mapping
     *                                for the key
     */
    public ValueType get(KeyType key) throws NoSuchElementException {
        if(containsKey(key)) {
            for (Pair<KeyType, ValueType> currPair : arr[Math.abs(key.hashCode()) % arr.length]) {
                if (currPair.getKey().equals(key)) {
                    return currPair.getValue();
                }
            }
        }
        //Runs if key is not contained
        throw new  NoSuchElementException();

    }

    /**
     * Removes a key and its value from the hashtableMap.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     * or null if the map did not contain a mapping for key
     */
    public ValueType remove(KeyType key) {
        if(containsKey(key)) {
                for (Pair<KeyType, ValueType> currPair : arr[Math.abs(key.hashCode()) % arr.length]) {
                    if (currPair.getKey().equals(key)) {
                        ValueType val = currPair.getValue();
                        arr[Math.abs(key.hashCode()) % arr.length].remove(currPair);
                        size--;
                        return val;
                    }
                }
            }
        return null;
    }

    /**
     * Checks if a key is stored in the hashtableMap.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the hashtableMap
     * and false otherwise
     */
    public boolean containsKey(KeyType key) {
        if(arr[Math.abs(key.hashCode()) % arr.length] != null ) {
            for (Pair<KeyType, ValueType> currPair : arr[Math.abs(key.hashCode()) % arr.length]) {
                if (currPair.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the number of (key, value) pairs stored in the hashtableMap.
     * @return the number of (key, value) pairs stored in the hashtableMap
     */
    public int size() {
        return size;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() {
        for(LinkedList<Pair<KeyType, ValueType>> list : arr) {
            if(list != null) {
                list.clear();
            }
        }
	size = 0;
    }
    /**
     * Resizes the hashtableMap and reinserts each (key, value) pair
     */
    @SuppressWarnings("unchecked")
    private void reHash() {
        LinkedList<Pair<KeyType, ValueType>>[] newArr;
        newArr = new LinkedList[arr.length * 2];
        for(LinkedList<Pair<KeyType, ValueType>> list : arr) {
            if(list != null) {
                for(Pair<KeyType, ValueType> currPair : list) {
                    if(newArr[Math.abs(currPair.getKey().hashCode()) % newArr.length] == null) {
                        LinkedList<Pair<KeyType, ValueType>> bucket = new LinkedList<>();
                        bucket.add(currPair);
                        newArr[Math.abs(currPair.getKey().hashCode()) % newArr.length] = bucket;
                    } else {
                        newArr[Math.abs(currPair.getKey().hashCode()) % newArr.length].add(currPair);
                    }
                }
            }

        }
        arr = newArr;
    }

    /**
     * Returns an iterator over elements of the hashTableMap
     *
     * @return an Iterator.
     */
    public Iterator<ValueType> iterator() {
        return new HashtableMapIterator<KeyType, ValueType>(this);
    }
}

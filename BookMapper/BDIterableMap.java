import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Placeholder class that simulates the implementation of the hashtable map that contains the books
 * @param <String> the key, specified by the ISBN number
 * @param <IBook> the value, which is the book object itself
 */
public class BDIterableMap<String, IBook> implements IterableMapADT<String, IBook>{
    protected int capacity;
    protected LinkedList<IBook>[] map;
    private int size;
    public BDIterableMap() {
        this.capacity = 15;
        this.map = new LinkedList[capacity];
        this.size = 0;
    }
    /**
     * Inserts a new (key, value) pair into the map if the map does not contain a value mapped to
     * key yet.
     *
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map, false if a mapping for key
     * already exists and the new (key, value) pair could not be inserted
     */
    @Override
    public boolean put(String key, IBook value) {
        map[0] = new LinkedList<>();
        map[0].add(value);
        size++;
        return true;
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping for the key
     */
    @Override
    public IBook get(String key) throws NoSuchElementException {
        return map[0].get(0);
    }

    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed, or null if the map did not
     * contain a mapping for key
     */
    @Override
    public IBook remove(String key) {
        return null; // unimplemented
    }

    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map and false otherwise
     */
    @Override
    public boolean containsKey(String key) {
        return false; // unimplemented
    }

    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    @Override
    public void clear() {
        // unimplemented
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        ArrayList<IBook> books= new ArrayList<>();
        books.add((IBook) new BDBook("1", "1", "1111111111111"));
        books.add((IBook) new BDBook("2", "2", "2222222222222"));
        books.add((IBook) new BDBook("3", "3", "3333333333333"));
        return books.iterator();
    }

    /**
     * Performs the given action for each element of the {@code Iterable} until all elements have
     * been processed or the action throws an exception.  Actions are performed in the order of
     * iteration, if that order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs side-effects that modify
     * the underlying source of elements, unless an overriding class has specified a concurrent
     * modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer action) {
        // unimplemented
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="../util/Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The spliterator returned
     * by the default implementation has poor splitting capabilities, is unsized, and does not
     * report any spliterator characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator spliterator() {
        return null; // unimplemented
    }
}

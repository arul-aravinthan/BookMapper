import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMapIterator<KeyType, ValueType> implements Iterator<ValueType>{
    private Pair<KeyType, ValueType> next;
    private int currIndex = 0;
    private LinkedList<Pair<KeyType, ValueType>>[] mapArr;
    public HashtableMapIterator(HashtableMap<KeyType, ValueType> map) {
        if(map.size() == 0) {
            next = null;
        }
        for(LinkedList<Pair<KeyType, ValueType>> currList : map.arr) {
            if(currList != null) {
                next = currList.getFirst();
                mapArr = map.arr;
                break;
            }
            currIndex++;
        }
    }

    /**
     * Returns true if the iteration has more elements.
     * @return true if the iteration has more elements
     */
    public boolean hasNext() {
        return next != null;

    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public ValueType next() {
        //Throws exception if no more elements
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        //Return value
        ValueType oldNextValue = next.getValue();
        //Tracks whether a new next was created
        boolean changed = false;
        //If current list has another pair, set it to next, otherwise iterate through the rest of the array
        // and set the first value to next
        if(mapArr[currIndex].indexOf(next) + 1 < mapArr[currIndex].size()) {
            next = mapArr[currIndex].get(mapArr[currIndex].indexOf(next) + 1);
            changed = true;
        } else {
            //Increment index to get next Linked List
            currIndex++;
            while(currIndex < mapArr.length) {
                if(mapArr[currIndex] != null) {
                    next = mapArr[currIndex].getFirst();
                    changed = true;
                    //Stop incrementing index if pair was found
                    break;
                }
                currIndex++;
            }
        }
        //If no next value was set, then set next to null so hasNext() returns that there are no more values
        if(!changed) {
            next = null;
        }

        return oldNextValue;
    }
}

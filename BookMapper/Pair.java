// --== CS400 Project One File Header ==--
// Name: Arul Aravinthan
// CSL Username: arul
// Email: aravinthan@wisc.edu
// Lecture 003 @2:25 p.m
// Notes to Grader: None

/** An object represeting a (key, value) pair
 * @author Arul Aravinthan
 */
public class Pair<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;
    /**
     * Initializes Pair with inputted key and value pair
     * @param key key of Pair to create
     * @param value value of Pair to create
     */
    public Pair(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }
}

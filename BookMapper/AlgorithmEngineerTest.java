import java.util.List;
import java.util.Scanner;

public class AlgorithmEngineerTest {

    public static void main(String[] args) {
        System.out.println("Algorithm Engineer Individual Test 1: " + printResult(test1()));
        System.out.println("Algorithm Engineer Individual Test 2: " + printResult(test2()));
        System.out.println("Algorithm Engineer Individual Test 3: " + printResult(test3()));
        System.out.println("Algorithm Engineer Individual Test 4: " + printResult(test4()));
        System.out.println("Algorithm Engineer Individual Test 5: " + printResult(test5()));
        System.out.println("Algorithm Engineer Integration Test 1: " + printResult(intTest1()));
        System.out.println("Algorithm Engineer Integration Test 2: " + printResult(intTest2()));
        System.out.println("Algorithm Engineer Partner (Data Wrangler) Test 1: " + printResult(partnerTest1()));
        System.out.println("Algorithm Engineer Partner (Data Wrangler) Test 2: " + printResult(partnerTest2()));


    }

    /**
     * Prints the result of a test as passed or failed
     */
    public static String printResult(boolean result) {
        if (result) {
            return "passed";
        } else {
            return "failed";
        }
    }

    /**
     * Testing the hasNext() method with 1 value in the array
     * Checks if the method returns true when there is a value, and false after one call of next()
     */
    public static boolean test1() {
        try {
            HashtableMap<String, String> hashMap = new HashtableMap<>(5);
            hashMap.put("key1", "val1");

            HashtableMapIterator<String, String> mapIterator = new HashtableMapIterator<>(hashMap);
            //hasNext() should return true since there is a value in the array
            if (!mapIterator.hasNext()) {
                return false;
            }
            mapIterator.next();
            //hasNext() should return false since there is no more values in the array
            if (mapIterator.hasNext()) {
                return false;
            }
            //Return true if tests pass
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Testing the hasNext() method with 0 values in the array
     * Checks if the method returns false when there is nothing in the array
     */
    public static boolean test2() {
        try {
            HashtableMap<String, String> hashMap = new HashtableMap<>(5);
            HashtableMapIterator<String, String> mapIterator = new HashtableMapIterator<>(hashMap);
            //hasNext() should return true since there is a value in the array
            if (mapIterator.hasNext()) {
                return false;
            }
            //Return true if test passes
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Testing the next() method
     * Checks if the method returns the correct values for 2 inputted pairs
     */
    public static boolean test3() {
        try {
            HashtableMap<Integer, String> hashMap = new HashtableMap<>(5);
            hashMap.put(1, "val1"); //First pair in hashmap
            hashMap.put(2, "val3"); //Second pair in hashmap

            HashtableMapIterator<Integer, String> mapIterator = new HashtableMapIterator<>(hashMap);
            //next() should return the first value
            if (!mapIterator.next().equals("val1")) {
                return false;
            }
            //next() should return the second value
            if (!mapIterator.next().equals("val3")) {
                return false;
            }
            //Returns true if tests pass
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Testing the validate() method
     * Checks if the method returns false when an  invalid ISBN is inputted
     */
    public static boolean test4() {
        try {
            ISBNValidator ISBNValidator = new ISBNValidator();

            //validate() Should return false, as it is an invalid ISBN
            if (ISBNValidator.validate("6330319892875")) {
                return false;
            }
            //Returns true if test passes
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Testing the validate() method
     * Checks if the method returns true when a valid ISBN is inputted
     */
    public static boolean test5() {
        try {
            ISBNValidator ISBNValidator = new ISBNValidator();

            //Should return true, as it is a valid ISBN
            if (!ISBNValidator.validate("9781861972712")) {
                return false;
            }
            //Returns true if test passes
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Testing the hasNext() method and next() method, which are internally called by searchByTitleWord(), with the Backend Code
     * Using both a hashmap and a BookMapper Backend, checking if both return the same result
     */
    public static boolean intTest1() {
        try {
            HashtableMap<String, Book> hashMap = new HashtableMap<>(5);
            Book book = new Book("A Short History of Nearly Everything", "Bill Bryson", "9780767908184");
            hashMap.put("9780767908184", book);
            HashtableMapIterator<String, Book> mapIterator = new HashtableMapIterator<>(hashMap);
            //Should return true, since there is a next value
            if (!mapIterator.hasNext()) {
                System.out.println("!!!");
                return false;
            }

            IBookMapperBackend backend = new BookMapperBackend();
            backend.addBook(book);
            //searchByTitleWord() uses next() and hasNext() internally to iterate over every value in the list.
            //If hasnext() returns true, and next() gets the correct book, it will be equal to the book inputted
            List<IBook> found = backend.searchByTitleWord("History");
            if (found.get(0) != book) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Testing the ISBN Validator with the Frontend Code
     * Checks if an invalid ISBN outputs that the ISBN is invalid
     */
    public static boolean intTest2() {
        try {
            TextUITester tester = new TextUITester("1\n91287\n4\n");
            BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new BookMapperBackend(), new ISBNValidator());
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            //Returns false if the output is not that the output is not valid
            if (!output.split("\n")[11].equals("ISBN not valid")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Checks the equals() method of Book
     */
    public static boolean partnerTest1() {
        try {
            Book book1 = new Book("1", "2", "3");
            Book book2 = new Book("1", "2", "3");
            Book book3 = new Book("3", "2", "1");
            //Should return true, since books are equal
            if (!book1.equals(book2)) {
                return false;
            }
            //Should return false
            if (book1.equals(book3)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks the getters of Book
     */
    public static boolean partnerTest2() {
        try {
            Book book1 = new Book("1", "2", "3");
            //Should return true since the values are equal
            if (!(book1.getTitle().equals("1") && book1.getAuthors().equals("2") && book1.getISBN13().equals("3"))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

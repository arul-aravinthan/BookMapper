import java.io.FileNotFoundException;
import java.util.List;

/**
 * Tests for the Data Wrangler classes, Book and BookLoader
 */
public class DataWranglerTest {
    
    /** 
     * @return boolean true if test was successful
     * Tests loadBooks() method with an invalid file path
     */
    public static boolean test1() {
        IBookLoader loader = new BookLoader();

        try {
            // Loads a file that doesn't exist
            loader.loadBooks("books____.csv");
            return false;
        } catch (FileNotFoundException e) {
            // File not found, good
        }

        // No issues, return true
        return true;
    }
    
    /** 
     * @return boolean true if test is successful
     * Tests loadBooks() with a valid file path
     */
    public static boolean test2 () {
        IBookLoader loader = new BookLoader();

        try {
            // Loads a file that does exist
            loader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            // File not found, but it should've been, return false
            return false;
        }

        // No issues, return true
        return true;
    }
    
    /** 
     * @return boolean true if all tests pass
     * Tests all the methods of the Book class
     */
    public static boolean test3 () { 
        IBookLoader loader = new BookLoader();

        try {
            List<IBook> books = loader.loadBooks("books.csv");

            // Gets first book in list
            IBook book = books.get(0);

            // Runs all the getter methods, ensures they don't cause errors
            book.getTitle();
            book.getAuthors();
            book.getISBN13();
        } catch (FileNotFoundException e) {
            // Some error, return false
            return false;
        }

        // No issues, return true
        return true;
    }
    
    /** 
     * @return boolean true if tests were successful
     * Checks that all ISBN strings are entirely numbers. This serves
     * to ensure that the splitting of the lines worked well; if a line
     * was split incorrectly it would likely result in a isbn string being
     * a non-number
     */
    public static boolean test4 () {
        IBookLoader loader = new BookLoader();

        try {
            List<IBook> books = loader.loadBooks("books.csv");

            // Go through every book
            for (IBook b : books) {
                String isbn = b.getISBN13();

                // Checks that all isbn strings are the correct length
                if (isbn.length() != 13)
                    return false;

                // Go through every character in the ISBN string
                for (int i = 0; i < isbn.length(); i++) {
                    char curChar = isbn.charAt(i);

                    // Ensure that the character is a digit, otherwise return false
                    if (curChar < '0' || curChar > '9')
                        return false;
                }
            }
        } catch (Exception e) {
            // Some error occurred when it shouldn't have; return false
            return false;
        }

        // No issues, return true
        return true;
    }
    
    /** 
     * @return boolean true if tests successful
     * Ensures that all the book class's methods equal themselves; that they return the same value each call.
     */
    public static boolean test5 () {
        IBookLoader loader = new BookLoader();

        try {
            List<IBook> books = loader.loadBooks("books.csv");

            // Gets first book in list
            IBook book = books.get(0);

            // Ensures the book methods return the same string each time
            if (!book.getTitle().equals(book.getTitle()))
                return false;
            if (!book.getAuthors().equals(book.getAuthors()))
                return false;
            if (!book.getISBN13().equals(book.getISBN13()))
                return false;
        } catch (FileNotFoundException e) {
            // Some error, return false
            return false;
        }

        // No issues, return true
        return true;
    }

    
    /** 
     * Ensures that adding all the books to a BookMapperBackend object works
     * @return boolean true if successful
     */
    private static boolean integrationTest1 () {
        try {
            IBookLoader bookLoader = new BookLoader(); 
            List<IBook> bookList = bookLoader.loadBooks("books.csv");
            IBookMapperBackend backend = new BookMapperBackend();
            for (IBook book : bookList) {
                backend.addBook(book);
            }

            // Confirm that backend & bookList have the same number of books in them
            if (backend.getNumberOfBooks() != bookList.size()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        } 

        return true;
    }

    /** 
     * Ensures that a few searches work in BookMapperBackend when all books are passed into it.
     * @return boolean true if successful
     */
    private static boolean integrationTest2 () {
        try {
            IBookLoader bookLoader = new BookLoader(); 
            List<IBook> bookList = bookLoader.loadBooks("books.csv");
            IBookMapperBackend backend = new BookMapperBackend();
            for (IBook book : bookList) {
                backend.addBook(book);
            }

            if (backend.getByISBN("9780618772452") == null)
                return false;
            if (backend.searchByTitleWord("The").size() == 0)
                return false;
        } catch (Exception e) {
            return false;
        } 

        return true;
    }
    
    /** 
     * Ensures that all the books have valid ISBN strings
     * @return boolean true if successful
     */
    private static boolean partnerTest1 () {
        try {
            IBookLoader bookLoader = new BookLoader(); 
            List<IBook> bookList = bookLoader.loadBooks("books.csv");
            IISBNValidator validator = new ISBNValidator();

            for (IBook book : bookList) {
                if (!validator.validate(book.getISBN13())) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        } 

        return true;
    }
    
    /** 
     * Ensures that the ISBN validator rejects invalid ISBN strings
     * @return boolean true if successful
     */
    private static boolean partnerTest2 () {
        try {
            IISBNValidator validator = new ISBNValidator();

            String[] invalidISBNs = {"1", "123", "1231231231231", "DIRYEOSNFBDFS"};

            // Ensure each one is invalid; if one is valid then return false
            for (String isbn : invalidISBNs) {
                if (validator.validate(isbn))
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
    /** 
     * @param args
     * Runs all test methods and prints results
     */
    public static void main(String[] args) {
        System.out.println("Data Wrangler Individual Test 1: " + (test1() ? "Success": "Fail"));
        System.out.println("Data Wrangler Individual Test 2: " + (test2() ? "Success": "Fail"));
        System.out.println("Data Wrangler Individual Test 3: " + (test3() ? "Success": "Fail"));
        System.out.println("Data Wrangler Individual Test 4: " + (test4() ? "Success": "Fail"));
        System.out.println("Data Wrangler Individual Test 5: " + (test5() ? "Success": "Fail"));

        System.out.println("Data Wrangler Integration Test 1: " + (integrationTest1() ? "Success": "Fail"));
        System.out.println("Data Wrangler Integration Test 2: " + (integrationTest2() ? "Success": "Fail"));
        
        System.out.println("Data Wrangler Partner (Alg Engineer) Test 1: " + (partnerTest1() ? "Success": "Fail"));
        System.out.println("Data Wrangler Partner (Alg Engineer) Test 2: " + (partnerTest2() ? "Success": "Fail"));
    }
}

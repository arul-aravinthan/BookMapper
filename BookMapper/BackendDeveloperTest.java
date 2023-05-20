import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class validates the behaviors of the BackMapperBackend class
 */
public class BackendDeveloperTest {
    /**
     * Checks the behavior of method addBook()
     * @return true if the method behaved correctly, false otherwise
     */
    public static boolean addBookTest() {
        IBookMapperBackend backend = new BookMapperBackend();
        BDBook firstBook = new BDBook("1", "1", "111111111111");
        backend.addBook(firstBook);
        if (!backend.getByISBN("111111111111").equals(firstBook) || backend.getNumberOfBooks() != 1) {
            System.out.println("Method addBook() does not correctly add a new book to the map");
            return false;
        }
        return true;
    }
    /**
     * Checks the behavior of method getNumberOfBooks()
     * @return true if the method behaved correctly, false otherwise
     */
    public static boolean getNumberOfBooksTest() {
        IBookMapperBackend backend = new BookMapperBackend();
        BDBook firstBook = new BDBook("1", "1", "111111111111");
        backend.addBook(firstBook);
        if (backend.getNumberOfBooks() != 1) {
            System.out.println("Method getNumberOfBooksTest() does not correctly return the " +
                    "number of books in the map");
            return false;
        }
        return true;
    }
    /**
     * Checks the behavior of methods setAuthorFilter(), getAuthorFilter() and resetAuthorFilter()
     * @return true if the methods behaved correctly, false otherwise
     */
    public static boolean authorFilterTest() {
        IBookMapperBackend backend = new BookMapperBackend();
        // get author filter
        if (backend.getAuthorFilter() != null) {
            System.out.println("Method getAuthorFilter() does not return the correct author " +
                    "filter");
            return false;
        }

        // set author filter
        backend.setAuthorFilter("1");
        if (!backend.getAuthorFilter().equals("1")) {
            System.out.println("Method setAuthorFilter() does not correctly set the author filter");
            return false;
        }

        // reset author filter
        backend.resetAuthorFilter();
        if (backend.getAuthorFilter() != null) {
            System.out.println("Method resetAuthorFilter() does not correctly reset the author " +
                    "filter");
            return false;
        }
        return true;
    }
    /**
     * Checks the behavior of method searchByTitleWord()
     * @return true if the method behaved correctly, false otherwise
     */
    public static boolean searchByTitleWordTest() {
        IBookMapperBackend backend = new BookMapperBackend();
        BDBook firstBook = new BDBook("1", "1", "111111111111");
        backend.addBook(firstBook);
        // result list is not empty
        List<IBook> result = backend.searchByTitleWord("1");
	if (result.size() != 1) {
	    System.out.println("Method searchByTitleWordTest() failed to correctly return a list with size " +
		    "one when only one book matched the filter");
	    return false;
	}
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).getTitle().contains("1")) {
                System.out.println("Method searchByTitleWordTest() failed to correctly return a " +
                        "list of books with title containing the specified word");
                return false;
            }
        }

        // result list is empty
        result = backend.searchByTitleWord("5");
        if (result.size() != 0) {
            System.out.println("Method searchByTitleWordTest() failed to correctly return an " +
                    "empty list when no books has the title containing the specified word");
            return false;
        }
        return true;
    }
    /**
     * Checks the behavior of method getByISBN()
     * @return true if the method behaved correctly, false otherwise
     */
    public static boolean getByISBNTest() {
        IBookMapperBackend backend = new BookMapperBackend();
        BDBook firstBook = new BDBook("1", "1", "111111111111");
        backend.addBook(firstBook);
        if (!backend.getByISBN("111111111111").equals(firstBook)) {
            System.out.println("Method getByISBNTest() failed to return the correct result when " +
                    "the ISBN of a book in the map is passed in");
            return false;
        }
        return true;
    }

    /**
     * Check backend implementation when integrated with other roles' code
     * @return true if behaved correctly, false otherwise
     */
    public static boolean IntegrationTest1() {
        IBookLoader bookLoader = new BookLoader();
	List<IBook> bookList = null;
	try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
	    return false;
        }        
	IBookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        if (backend.getNumberOfBooks() != 11124) {
            return false;
        }

        backend.setAuthorFilter("Smylie");
        List<IBook> result = backend.searchByTitleWord("adventures");
        if (result.size() != 1 || !result.get(0).getISBN13().equals("9781932386103")) {
            return false;
        }

        if (!backend.getByISBN("9781932386103").getAuthors().equals("Mark Smylie")
                || !backend.getByISBN("9781932386103").getTitle().equals("Artesia: Adventures in " +
                "the Known World")) {
            return false;
        }
        return true;
    }

    /**
     * Check backend implementation when integrated with other roles' code
     * @return true if behaved correctly, false otherwise
     */
    public static boolean IntegrationTest2() {
        IBookLoader bookLoader = new BookLoader();
        List<IBook> bookList = null;
	try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
	    return false;
        }
	IBookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        if (backend.getNumberOfBooks() != 11124) {
            return false;
        }

        backend.setAuthorFilter("Smylie");
        backend.resetAuthorFilter();
        List<IBook> result = backend.searchByTitleWord("moby");
        if (result.size() != 15) {
            return false;
        }

        if (!backend.getByISBN("9780312876067").getAuthors().equals("Orson Scott Card")
                || !backend.getByISBN("9780312876067").getTitle().equals("Saints")) {
            return false;
        }
        return true;
    }
    
    /**
     * Check the correctness of partner's code
     * @return true if the code behaved correctly, false otherwise
     */
    public static boolean PartnerTest1() {
	TextUITester tester = new TextUITester("32\n1\n9780312876067\n4\n");
        IBookLoader bookLoader = new BookLoader();
        List<IBook> bookList = null;
        try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        IBookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        IISBNValidator isbnValidator = new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        frontend.runCommandLoop();
        String output = tester.checkOutput();
	if (!output.split("\n")[8].equals("Invalid Choice, please re-enter an integer between 1 and 4")) {
	    return false;		
	}
        if (!output.split("\n")[17].equals("1. \"Saints\" by Orson Scott Card, ISBN: 9780312876067")) {
            return false;
        }
        return true;        
    }

    /**
     * Check the correctness of partner's code
     * @return true if the code behaved correctly, false otherwise
     */
    public static boolean PartnerTest2() {
        TextUITester tester = new TextUITester("2\nFive Little Peppers at School\n4\n");
        IBookLoader bookLoader = new BookLoader();
        List<IBook> bookList = null;
        try {
            bookList = bookLoader.loadBooks("books.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        IBookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        IISBNValidator isbnValidator = new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        frontend.runCommandLoop();
        String output = tester.checkOutput();
	if (!output.split("\n")[13].equals("1. \"Five Little Peppers at School\" by Margaret Sidney/Barbara Cooney, ISBN: 9780440400356")) {
            return false;
        }
        if (!output.split("\n")[21].equals("Goodbye!")) {
            return false;
        }
        return true;
    }

    /**
     * Helper method to display the correct result of a test
     * @param result test result
     * @return the string "passed" if result is true, and "failed" otherwise
     */
    private static String testResult(boolean result) {
	return (result ? "passed" : "failed");        
    }

    /**
     * Runs all tests
     * @return true if all tests return true, false otherwise
     */
    public static void runAllTests() {
        System.out.println("Backend Developer Individual Test 1: " + testResult(addBookTest()));
        System.out.println("Backend Developer Individual Test 2: " + testResult(getNumberOfBooksTest()));
        System.out.println("Backend Developer Individual Test 3: " + testResult(authorFilterTest()));
        System.out.println("Backend Developer Individual Test 4: " + testResult(searchByTitleWordTest()));
        System.out.println("Backend Developer Individual Test 5: " + testResult(getByISBNTest()));
        System.out.println("Backend Developer Integration Test 1: " + testResult(IntegrationTest1()));
        System.out.println("Backend Developer Integration Test 2: " + testResult(IntegrationTest2()));
	System.out.println("Backend Developer Partner Test 1: " + testResult(PartnerTest1()));
	System.out.println("Backend Developer Partner Test 2: " + testResult(PartnerTest2()));
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[]args) {
        runAllTests();
    }
}

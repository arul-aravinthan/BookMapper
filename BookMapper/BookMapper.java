import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {
    public static void main(String[] args) throws FileNotFoundException {
	IBookLoader bookLoader = new BookLoader(); 
        List<IBook> bookList = bookLoader.loadBooks("books.csv");
        IBookMapperBackend backend = new BookMapperBackend();
        for (IBook book : bookList) {
            backend.addBook(book);
        }
        IISBNValidator isbnValidator = new ISBNValidator();
        Scanner userInputScanner = new Scanner(System.in);
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        frontend.runCommandLoop();
    }
    
}

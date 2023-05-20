import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * BookLoader.java
 * Reads a file and returns a list of IBook objects in the file
 */
public class BookLoader implements IBookLoader {
    
    /** 
     * @param filepathToCSV Path to the CSV file
     * @return List<IBook> List of books
     * @throws FileNotFoundException Thrown if CSV file not found
     */
    public List<IBook> loadBooks (String filepathToCSV) throws FileNotFoundException {
        // Create a list that will store the books
        List<IBook> books = new ArrayList<IBook>();

        // Read in the file, create a scanner for it. Specifies the encoding of the file
        File f = new File(filepathToCSV);
        Scanner scanner = new Scanner(f, "UTF-8");

        // Reads the header line, splits it at the commas
        String headerLine = scanner.nextLine();
        String[] headers = headerLine.split(",");

        int nCols = headers.length;

        // Finds the indices of the desired header columns
        int titleIndex = strArrayIndexOf(headers, "title");
        int authorsIndex = strArrayIndexOf(headers, "authors");
        int isbnIndex = strArrayIndexOf(headers, "isbn13");

        // Runs through each line in the file of books
        while (scanner.hasNextLine()) {
            // Reads the next line, and creates an array to store all the data about the line
            String curBook = scanner.nextLine();
            String[] data = new String[nCols];

            // Runs through the line, figuring out how to split it at the commas, while accounting for quotations
            int arrI = 0;
            int strIndex = 0;
            int nQuotes = 0;
            int startI = 0;
            while (strIndex < curBook.length()) {
                if (curBook.charAt(strIndex) == '"') {
                    // If the character is a quotation mark (") not preceded by a backslash (\), incremenet nQuotes by 1
                    if (strIndex == 0 || curBook.charAt(strIndex-1) != '\\') {
                        nQuotes++;
                    }
                } else if (curBook.charAt(strIndex) == ',') {
                    // If the character is a comma, check to see that there isn't an unopened quotation
                    if (nQuotes % 2 == 0) {
                        // If there aren't any unopen quotations, store the text since the last comma in index arrI of the array, and increment arrI
                        // If there were quotations, remove them by cutting off the first and last characters
                        String curString = curBook.substring(startI + (nQuotes == 2 ? 1 : 0), strIndex - (nQuotes == 2 ? 1 : 0));

                        // Replace all instances of \" with "
                        curString = curString.replace("\\\"", "\"");

                        // Store this string in the next available index of the array
                        data[arrI] = curString;

                        // Move onto the next index, reset the number of quotes to 0, and increment the start index (since the next text will start after the comma)
                        arrI++;
                        nQuotes = 0;
                        startI = strIndex + 1;
                    }
                }
                strIndex++;
            }
            // Here, the string is done. So finally store the remaining text in the last index of the array
            data[arrI] = curBook.substring(startI + (nQuotes == 2 ? 1 : 0), strIndex - (nQuotes == 2 ? 1 : 0));

            // Create a book object with the desired properties, add it to the list
            String title = data[titleIndex], authors = data[authorsIndex], isbn = data[isbnIndex];
            books.add(new Book(title, authors, isbn));
        }
        scanner.close();

        // Return the list
        return books;
    }

    
    /** 
     * @param arr String array to search through
     * @param search String to search for
     * @return int index of string, -1 if not found
     * Searches for a value in a string array; used to figure out which column stores which piece of info
     */
    private int strArrayIndexOf (String[] arr, String search) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(search))
                return i;
        }
        return -1;
    }

    
    /** 
     * @param args
     * @throws FileNotFoundException if file isn't found
     * Code for testing
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<IBook> books = new BookLoader().loadBooks("books.csv");

        String st = "";
        for (IBook book : books) {
            String cur = book.getTitle() + ":" + book.getAuthors() + ":" + book.getISBN13();
            st += cur + "\n";
            // System.out.println(cur);
        }
   }
}

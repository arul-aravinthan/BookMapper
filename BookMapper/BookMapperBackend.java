import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class contains the implementation of the backend methods of a hashtable map containing books
 */
public class BookMapperBackend implements IBookMapperBackend {
    HashtableMap<String, IBook> map = new HashtableMap<String, IBook>();
    String filter = null;
    /**
     * Adds a new book to the backend's database and is stored in a hash table internally.
     *
     * @param book the book to add
     */
    @Override
    public void addBook(IBook book) {
        map.put(book.getISBN13(), book);
    }

    /**
     * Returns the number of books stored in the backend's database.
     *
     * @return the number of books
     */
    @Override
    public int getNumberOfBooks() {
        return map.size();
    }

    /**
     * This method can be used to set a filter for the author names contained in the search results.
     * A book is only returned as a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     *
     * @param filterBy the string that the book's author names must contain
     */
    @Override
    public void setAuthorFilter(String filterBy) {
        this.filter = filterBy.toLowerCase();
    }

    /**
     * Returns the string used as the author filter, null if no author filter is currently set.
     *
     * @return the string used as the author filter, or null if none is set
     */
    @Override
    public String getAuthorFilter() {
        return this.filter;
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetAuthorFilter() {
        this.filter = null;
    }

    /**
     * Search through all the books in the title base and return books whose title contains the
     * string word (and that satisfies the author filter, if an author filter is set).
     *
     * @param word word that must be contained in a book's title in result set
     * @return list of books found
     */
    @Override
    public List<IBook> searchByTitleWord(String word) {
        List<IBook> results = new ArrayList<>();
        Iterator iterator = map.iterator();
        while (iterator.hasNext()) {
            IBook book = (IBook) iterator.next();
            String tempFilter;
            if (filter != null) {
                tempFilter = filter;
            }
            else {
                tempFilter = "";
            }
            if (book.getTitle().toLowerCase().contains(word.toLowerCase()) && book.getAuthors().toLowerCase().contains(tempFilter)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Return the book uniquely identified by the ISBN, or null if ISBN is not present in the
     * dataset.
     *
     * @param ISBN the book's ISBN number
     * @return the book identified by the ISBN, or null if ISBN not in database
     */
    @Override
    public IBook getByISBN(String ISBN) {
        try {
            return (IBook) map.get(ISBN);
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }
}

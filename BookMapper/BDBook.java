/**
 * Placeholder class that simulates the implementation of the object Book
 */
public class BDBook implements IBook {
    String title;
    String authors;
    String ISBN;
    public BDBook(String title, String authors, String ISBN){
        this.title = title;
        this.authors = authors;
        this.ISBN = ISBN;
    }
    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string that contains the authors of the book as a single string with different
     * authors separated by /.
     *
     * @return author names as single string
     */
    @Override
    public String getAuthors() {
        return authors;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13() {
        return ISBN;
    }

    /**
     * Helper method that compares the two book objects
     * @param target the object that this book is compared to
     * @return true if the two books are the same, false otherwise
     */
    public boolean equals(BDBook target) {
        if (target == null) {
            return false;
        }
        if (this.title.equals(target.getTitle()) && this.authors.equals(target.getAuthors()) && this.ISBN.equals(target.getISBN13())) {
            return true;
        }
        else {
            return false;
        }
    }
}

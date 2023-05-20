/**
 * Book.java
 * Represents a book object
 */
public class Book implements IBook {
    private String title, authors, isbn;

    /**
     * @param title of book
     * @param authors of book
     * @param isbn number of book
     */
    public Book (String title, String authors, String isbn) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    } 

    
    /** 
     * @return String returns title of book
     */
    public String getTitle () {
        return title;
    }

    
    /** 
     * @return String returns authors
     */
    public String getAuthors () {
        return authors;
    }

    
    /** 
     * @return String returns ISBN string of book
     */
    public String getISBN13 () {
        return isbn;
    }

    /** 
     * @param other other object; should be a book
     * @return boolean true if book objects are equal
     */
    public boolean equals (Object other) {
        if (other.getClass() != this.getClass())
            return false;
        Book o = (Book) other;

        return this.getAuthors().equals(o.getAuthors()) && this.getISBN13().equals(o.getISBN13()) && this.getTitle().equals(o.getTitle());
    }
}

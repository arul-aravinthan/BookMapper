import java.util.Arrays;
import java.util.List;

public class FDBookMapperBackend implements IBookMapperBackend {
	
	protected String AuthorFilter;
	
	class Book1 implements IBook {

		@Override
		public String getTitle() {
			return "Title1";
		}

		@Override
		public String getAuthors() {
			return "Author1, Author2";
		}

		@Override
		public String getISBN13() {
			return "3427584928372";
		}
		
	}
	
	class Book2 implements IBook {

		@Override
		public String getTitle() {
			return "Title2";
		}

		@Override
		public String getAuthors() {
			return "Author3, Author4";
		}

		@Override
		public String getISBN13() {
			return "4427584928372";
		}
		
	}
	
	@Override
	public void addBook(IBook book) {
		//unimplemented		
	}

	@Override
	public int getNumberOfBooks() {
		return 2;
	}

	@Override
	public void setAuthorFilter(String filterBy) {
		this.AuthorFilter = filterBy;
		
	}

	@Override
	public String getAuthorFilter() {
		return this.AuthorFilter;
	}

	@Override
	public void resetAuthorFilter() {
		this.AuthorFilter = null;
	}

	@Override
	public List<IBook> searchByTitleWord(String word) {
		return Arrays.asList(new IBook[] {new Book1(), new Book2()});
	}

	@Override
	public IBook getByISBN(String ISBN) {
		return new Book1();
	}

}

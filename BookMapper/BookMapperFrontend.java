import java.util.List;
import java.util.Scanner;

public class BookMapperFrontend implements IBookMapperFrontend {
	Scanner scanner;
	IBookMapperBackend fDBookMapperBackend;
    IISBNValidator fDISBNValidator;
    boolean head = false;
	public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator) {
		this.scanner = userInputScanner;
		this.fDBookMapperBackend = backend;
		this.fDISBNValidator = validator;
		
	}
	
	// to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():
	@Override
	public void runCommandLoop() {
		if(head==false)
		{
			System.out.println("Welcome to the Book Mapper Application!");
	        System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
	        System.out.println(" ");
			head = true;
		}
		displayMainMenu();
		int command = getIntInput();
        String option = " ";
        switch (command) {
            case 1:
            	option = "Lookup ISBN";
            	printWhichOption(option);
            	isbnLookup();
                break;
            case 2:
            	option = "Search by Title Word";
            	printWhichOption(option);
            	titleSearch();
                break;
            case 3:
            	option = "Set Author Name Filter";
            	printWhichOption(option);
            	if(fDBookMapperBackend.getAuthorFilter() == null)
            	{
            		System.out.println("Author name must currently contain: none\n");
            	}
            	else {
            		System.out.println("Author name must currently contain: " + fDBookMapperBackend.getAuthorFilter() + "\n");
            	}
            		System.out.println("Enter a new string for author names to contain (empty for any):");
            		String filtBy = this.scanner.nextLine();
            		fDBookMapperBackend.setAuthorFilter(filtBy);
            		if(filtBy.isEmpty())
            		{
            			fDBookMapperBackend.resetAuthorFilter();
            		}          	
            	
                break;
            case 4:
            	option = "Exit Application";
            	System.out.println("Goodbye!\n");
            	return;          
            default:
                System.out.println("Invalid Choice, please re-enter an integer between 1 and 4");  
        }
        // Loop through menus again
        runCommandLoop();
	}
	
	public void printWhichOption(String option) {
		System.out.println("You are in the " + option + " Menu:" + "\n");
	}
	
	// prints command options to System.out
	@Override
	public void displayMainMenu() {
		// TODO Auto-generated method stub
		//System.out.println("Welcome to the Book Mapper Application!");
        //System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
        //System.out.println(" ");
        System.out.println("You are in the Main Menu:");
        System.out.println("1) Lookup ISBN");
        System.out.println("2) Search by Title Word");
        System.out.println("3) Set Author Name Filter");
        System.out.println("4) Exit Application");
        }
	
	public int getIntInput() {
        // Method to check for input        
        String input = this.scanner.nextLine();
        int cmd = 1;
        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If not a number
            System.out.println("Invalid number option chosen");
            cmd = getIntInput();
        }
        return cmd;
    }
	
	
	// displays a list of books
	@Override
	public void displayBooks(List<IBook> books) {
        for(int i=0; i<books.size(); i++)
        {
        	System.out.println((i+1) + ". \"" + books.get(i).getTitle() + "\" by " + books.get(i).getAuthors() + ", ISBN: " + books.get(i).getISBN13()+"\n");
        	//System.out.println("\n");
        }
	}
	
	
	// reads word from System.in, displays results
	@Override
	public void isbnLookup() {
		System.out.println("Enter ISBN to look up:");
        String inputIsbn = scanner.nextLine(); 
        if(fDISBNValidator.validate(inputIsbn))
        {
        	System.out.println("1. \"" + fDBookMapperBackend.getByISBN(inputIsbn).getTitle() + "\" by " + fDBookMapperBackend.getByISBN(inputIsbn).getAuthors() + ", ISBN: " + fDBookMapperBackend.getByISBN(inputIsbn).getISBN13() + "\n");
        } else  {
			System.out.println("ISBN not valid");
		}

	}
	
	
	// reads author name from System.in, displays results
	@Override
	public void titleSearch() {
		String authorFilter = "";
		System.out.println("Enter a word to search for in book titles (empty for all books):");
        String inputTitle = scanner.nextLine();
        if(fDBookMapperBackend.getAuthorFilter() == null)
        {
        	authorFilter ="any author";
        	 System.out.println("Matches (" + authorFilter + ") " + fDBookMapperBackend.searchByTitleWord(inputTitle).size() +" of " + fDBookMapperBackend.getNumberOfBooks() + "\n");
             //System.out.println("\n");
             displayBooks(fDBookMapperBackend.searchByTitleWord(inputTitle));
             System.out.println("Matches (" + authorFilter + ") " + fDBookMapperBackend.searchByTitleWord(inputTitle).size() +" of " + fDBookMapperBackend.getNumberOfBooks());
        }
        else
        {
        	authorFilter = "author filter: " + fDBookMapperBackend.getAuthorFilter();        
        	System.out.println("Matches (" + authorFilter + ") " + fDBookMapperBackend.searchByTitleWord(inputTitle).size() +" of " + fDBookMapperBackend.getNumberOfBooks() + "\n");
        	//System.out.println("\n");
        	displayBooks(fDBookMapperBackend.searchByTitleWord(inputTitle));
        	System.out.println("Matches (" + authorFilter + ") " + fDBookMapperBackend.searchByTitleWord(inputTitle).size() +" of " + fDBookMapperBackend.getNumberOfBooks());
        }
    }

       
}

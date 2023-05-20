import java.util.Scanner;

public class FrontendDeveloperTest {
	
	//Test1: The Test1 is designed to test whether the case-switch functions.
	//The only input after the frontend run is the integer 4
	//The range of the input should be any integers between 1 to 4
	//If it is not a integer, the frontend will return Invalid command
	//If it is an integer but not in the range of [1,4], 
	//the frontend will ask the user to re-enter a valid integer.
	//only after inputting the value 4 properly, the frontend will return "Goodbye"
	//If it returns exactly the same, the boolean value of test1() should be true
	
	public static boolean test1() {
		boolean rtn = false;
		TextUITester tester = new TextUITester("4\n");
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),new FDBookMapperBackend(), new FDISBNValidator());			
		frontend.runCommandLoop();
		String output = tester.checkOutput();		
		if(output.split("\n")[8].equals("Goodbye!")) {
			rtn=true;
		}
		return rtn;
	}
	

	//Test2: The Test2 is designed to test whether the command 1) Lookup ISBN functions.
	//The first input after the frontend run is the integer 1
	//The range of the input should be any integers between 1 to 4
	//If it is not a integer, the frontend will return Invalid command
	//If it is an integer but not in the range of [1,4], 
	//the frontend will ask the user to re-enter a valid integer.
	//only after inputting the value 4 properly, the frontend is able to received an ISBN string
	//and if the provided ISBN string is valid, then the frontend will look up in the backend database
	//In this case, it will look up in the placeholder and return a fake book with the information 1. \"Title1\" byAuthor1, Author2, ISBN: 3427584928372
	//If it returns exactly the same, the boolean value of test2() should be true
	
	public static boolean test2() {
		boolean rtn = false;
		TextUITester tester = new TextUITester("1\nsomevalidISBN\n4\n");
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),new FDBookMapperBackend(), new FDISBNValidator());			
		frontend.runCommandLoop();
		String output = tester.checkOutput();		
		//System.out.println(output);
		if(output.split("\n")[11].equals("1. \"Title1\" by Author1, Author2, ISBN: 3427584928372")) {
			rtn=true;
		}
		return rtn;
	}
	
	//Test3: The Test3 is designed to test whether the command 2) Lookup Search for Title Word Menu functions.
		//The first input after the frontend run is the integer 2
		//The range of the input should be any integers between 1 to 4
		//If it is not a integer, the frontend will return Invalid command
		//If it is an integer but not in the range of [1,4], 
		//the frontend will ask the user to re-enter a valid integer.
		//only after inputting the value 2 properly, the frontend is able to received an string representing the title search
		// it will tell the user: Enter a word to search for in book titles (empty for all books)
		//After the string is provided, then the frontend will look up in the backend database
		//In this case, it will look up in the placeholder while in true cases, it will iterate the database
		//It is supposed to return the strings below
		//If it returns exactly the same, the boolean value of test3() should be true
	
	public static boolean test3() {
		boolean rtn = false;
		TextUITester tester = new TextUITester("2\nsomeTitle\n4\n");
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),new FDBookMapperBackend(), new FDISBNValidator());			
		frontend.runCommandLoop();
		String output = tester.checkOutput();		
		String [] s= output.split("\n");
		String ln9 = "You are in the Search by Title Word Menu:";
		String ln10 = "Enter a word to search for in book titles (empty for all books):";
		String ln11 = "Matches (any author) 2 of 2";
		String ln13 = "1. \"Title1\" by Author1, Author2, ISBN: 3427584928372";
		String ln15 = "2. \"Title2\" by Author3, Author4, ISBN: 4427584928372";
		if((s[8].equals(ln9))) {
			rtn=true;
		}
		if((s[8].equals(ln9))&&(s[9].equals(ln10))&&(s[10].equals(ln11))&&(s[12].equals(ln13))&&(s[14].equals(ln15))&&(s[16].equals(ln11))) {
			rtn=true;
		}
		return rtn;
	}
	
	//Test4: The Test4 is designed to test whether the command 3) Set Author Filter Menu functions.
			//The first input after the frontend run is the integer 3
			//The range of the input should be any integers between 1 to 4
			//If it is not a integer, the frontend will return Invalid command
			//If it is an integer but not in the range of [1,4], 
			//the frontend will ask the user to re-enter a valid integer.
			//only after inputting the value 3 properly, the frontend is able to load the next step
			// it will tell the user: You are in the Set Author Name Filter Menu:
			//In this case, the user is not supposed to provide the Author name string, so a null is inputed
			//In this case, it will look up in the placeholder while in true cases, it will iterate the database
			//It is supposed to return Author name must currently contain: none
			//If it returns exactly the same, the boolean value of test4() should be true
	
	public static boolean test4() {
		boolean rtn = false;
		TextUITester tester = new TextUITester("3\n"+null+"\n4\n");
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),new FDBookMapperBackend(), new FDISBNValidator());			
		frontend.runCommandLoop();
		String output = tester.checkOutput();		
		//System.out.println(output);
		String []s = output.split("\n");
		String ln9 = "You are in the Set Author Name Filter Menu:";
		String ln11 = "Author name must currently contain: none";
		//System.out.println(s[8]);
		//System.out.println(ln9);
		//System.out.println(s[10]);
		//System.out.println(ln11);
		if(s[8].equals(ln9)&&s[10].equals(ln11)) {
			rtn=true;
		}
		return rtn;
	}
	
	//Test5: The Test5 is designed to test whether the command 3) Set Author Filter Menu functions.
	//The first input after the frontend run is the integer 3
	//The range of the input should be any integers between 1 to 4
	//If it is not a integer, the frontend will return Invalid command
	//If it is an integer but not in the range of [1,4], 
	//the frontend will ask the user to re-enter a valid integer.
	//only after inputting the value 3 properly, the frontend is able to load the next step
	// it will tell the user: You are in the Set Author Name Filter Menu:
	//Differ from test4(), the user is supposed to provide the Author name string
	//Using the placeholder, the user provides some Author name(s)
	//In this case, it will look up in the placeholder while in true cases, it will iterate the database
	//It is supposed to return Author name must currently contain: SomeAuthor
	//If it returns exactly the same, the boolean value of test5() should be true
	
	public static boolean test5() {
		boolean rtn = false;
		TextUITester tester = new TextUITester("3\nsomeAuthor\n4\n");
		FDBookMapperBackend backend = new FDBookMapperBackend();
		backend.setAuthorFilter("SomeAuthor");
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),backend, new FDISBNValidator());			
		frontend.runCommandLoop();
		String output = tester.checkOutput();		
		//System.out.println(output);
		String []s = output.split("\n");
		String ln9 = "You are in the Set Author Name Filter Menu:";
		String ln11 = "Author name must currently contain: SomeAuthor";
		//System.out.println(s[8]);
		//System.out.println(ln9);
		//System.out.println(s[10]);
		//System.out.println(ln11);
		if(s[8].equals(ln9)&&s[10].equals(ln11)) {
			rtn=true;
		}
		return rtn;
	}

	public static boolean integrationTest1(){
		boolean rtn = false;
		TextUITester tester = new TextUITester("1\n9780330491198\n4\n");
		BookMapperBackend bookMapperBackend = new BookMapperBackend();
		Book book1 = new Book("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)", "Douglas Adams","9780330491198");
		bookMapperBackend.addBook(book1);
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),bookMapperBackend, new ISBNValidator());
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		//System.out.println(output);
		if(output.split("\n")[11].equals("1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198")) {
			rtn=true;
		}
		return rtn;
	}

	public static boolean integrationTest2(){
		boolean rtn = false;
		TextUITester tester = new TextUITester("2\nGuide\n4\n");
		BookMapperBackend bookMapperBackend = new BookMapperBackend();
		Book book2 = new Book("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)", "Douglas Adams","9780330491198");
		bookMapperBackend.addBook(book2);
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),bookMapperBackend, new ISBNValidator());
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		//System.out.println(output);
		if(output.split("\n")[13].equals("1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198")) {
			rtn=true;
		}
		return rtn;
	}

	public static boolean partnerTest1(){
		boolean rtn = false;
		TextUITester tester = new TextUITester("3\nDouglas\n2\n\n4\n");
		BookMapperBackend bookMapperBackend = new BookMapperBackend();
		Book book1 = new Book("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)", "Douglas Adams","9780330491198");
		bookMapperBackend.addBook(book1);
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),bookMapperBackend, new ISBNValidator());
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		//System.out.println(output);
		if(output.split("\n")[23].equals("1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198")) {
			rtn=true;
		}
		return rtn;
	}

	public static boolean partnerTest2(){
		boolean rtn = false;
		TextUITester tester = new TextUITester("3\nDouglas\n2\nGalaxy\n4\n");
		BookMapperBackend bookMapperBackend = new BookMapperBackend();
		Book book1 = new Book("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)", "Douglas Adams","9780330491198");
		bookMapperBackend.addBook(book1);
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),bookMapperBackend, new ISBNValidator());
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		//System.out.println(output);
		if(output.split("\n")[23].equals("1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198")) {
			rtn=true;
		}
		return rtn;
	}

	public static void main(String[] args) {
		System.out.println("Frontend Developer Individual Test 1:" + (test1() ? "passed": "failed"));
		System.out.println("Frontend Developer Individual Test 2:" + (test2() ? "passed": "failed"));
		System.out.println("Frontend Developer Individual Test 3:" + (test3() ? "passed": "failed"));
		System.out.println("Frontend Developer Individual Test 4:" + (test4() ? "passed": "failed"));
		System.out.println("Frontend Developer Individual Test 5:" + (test5() ? "passed": "failed"));
		System.out.println("Frontend Developer Integration Test 1: " + (integrationTest1() ? "passed": "Fail"));
		System.out.println("Frontend Developer Integration Test 2: " + (integrationTest2() ? "passed": "Fail"));
		System.out.println("Frontend Developer Backend Developer Partner Test 1: " + (partnerTest1() ? "passed": "Fail"));
		System.out.println("Frontend Developer Backend Developer Partner Test 2: " + (partnerTest2() ? "passed": "Fail"));

	}

}

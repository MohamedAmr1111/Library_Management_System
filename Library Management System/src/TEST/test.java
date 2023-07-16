package TEST;
import operations.Book;
import operations.Customer;
import operations.CustomerType;
import operations.Loan;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class test {

	Customer c1;
	Book b;
	private  Scanner scanner;
	private  List<Book> books;
	private  List<Customer> customers;
	private  List<Loan> loans;
	public test()
	{
		scanner= new Scanner(System.in);
		books = new ArrayList<>();
		customers = new ArrayList<>();
		loans = new ArrayList<>();
	}
	
	public void startApp() throws IOException
	{
		
		int number = -1;
		while(number!=0)
		{
			menuoptions();
			number=scanner.nextInt();
			switch(number)
			{
			case 0:
				break;
			case 1:
				AddBook();
				break;
			case 2:
				AddCustomer();
				break;
			case 3:
				showcustomer();
				break;
			case 4:
				returnBookFromCustomer();
				break;
			case 5:
				showBooks(false);
				break;
			case 6:
				c1.writeCustomersInFile(this.customers);
				break;
			case 7:
				showLoans();
				break;
			case 8:
				showCustomerLoans();
				break;
			case 9:
				loanBookForCustomer();
				break;
			case 10:
				b.writeBookInFile(this.books);
				break;
				default:
				System.out.println("Please enter right number");

			}
		}
		System.out.println("thanks");
	}
	
	private void menuoptions()
	{
		System.out.println("0. Quit");
		System.out.println("1. Add book");
		System.out.println("2. Add customer");
		System.out.println("3. Show customers");
		System.out.println("4. return book from customer");
		System.out.println("5. Show books");
		System.out.println("6. read customers from file");
		System.out.println("7. show loans");
		System.out.println("8. show customers loans");
		System.out.println("9. loan book for customer");
		System.out.println("10. read Book from file");

	}
	
	private void AddBook() throws IOException
	{
		System.out.println("=================");
		System.out.println("Adding new book");

		System.out.println("Please enter book title");
		String title = scanner.next();

		System.out.println("Please enter book author");
		String author = scanner.next();

		System.out.println("Please enter book subject");
		String subject = scanner.next();

		System.out.println("Please enter book price");
		double borrowPrice = scanner.nextDouble();
		
		Book book = new Book(title,author,subject,borrowPrice);
		this.books.add(book);
		book.writeBookInFile(this.books);
	}
	
	private void AddCustomer() throws IOException
	{
		System.out.println("===========================");
		System.out.println("please enter your name");
		String name = scanner.next();
		
		System.out.println("please enter your age");
		int age = scanner.nextInt();
		
		System.out.println("please enter your phone number");
		String phonenumber = scanner.next();
		
		CustomerType customer = getcutomertype();
		
		Customer c = new Customer(name,age,phonenumber,customer);
		this.customers.add(c);
		c.writeCustomersInFile(this.customers);
	}
	
	private CustomerType getcutomertype()
	{
		System.out.println("please 1 for GOLDEN and 2 for NORMAL");
		int number=scanner.nextInt();
		CustomerType customertype=CustomerType.NORMAL;
		switch(number)
		{
		case 1:
			customertype = CustomerType.GOLDEN;
			break;
		case 2:
			customertype=CustomerType.NORMAL;
		}
		
		return customertype;
	}
	
	private boolean showcustomer()
	{
		if(customers.isEmpty())
		{
			System.out.println("No customers available");
			return false;
		}
		for(int i = 0; i <customers.size();i++)
		{
			System.out.println(customers.get(i).toString());
		}
		return true;
	}
	
	private void returnBookFromCustomer() {
		System.out.println("=================");
		System.out.println("Return book from customer");
		Customer customer = selectCustomer();
		if (customer != null) {
			Loan loan = selectNotReturenedLoan(customer);
			if(loan != null) {
				customer.returnLoan(loan);
			}
		}
	}
	
	private Loan selectNotReturenedLoan(Customer customer) {
		System.out.println("Select a not paid loan");
		boolean loansAvailable = customer.showCustomerLoans(true);
		if (loansAvailable) {
			return customer.getLoans().get(validateAndReadInput(1, customer.getLoans().size()) - 1);
		}
		return null;
	}

	private boolean showBooks(boolean notIssuedOnly) {
		if (books.isEmpty()) {
			System.out.println("No books available!");
			return false;
		}
		int count = 0;
		for (int i = 0; i < books.size(); i++) {
			if (notIssuedOnly && !books.get(i).isIssued() || !notIssuedOnly) {
				System.out.println(books.get(i).toString());
				count++;
			}
		}
		if (count == 0) {
			System.out.println("No free books available!");
			return false;
		}
		return true;
	}
	
	private boolean showCustomers() {
		if (customers.isEmpty()) {
			System.out.println("No customers available!");
			return false;
		}
		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i).toString());
		}
		return true;
	}
	
	
	private boolean showLoans() {
		if (loans.isEmpty()) {
			System.out.println("No loans available!");
			return false;
		}
		for (int i = 0; i < loans.size(); i++) {
			System.out.println(loans.get(i).toString());
		}
		return true;
	}
	
	
	private void showCustomerLoans() {
		System.out.println("=================");
		System.out.println("Showing A customer loans");
		Customer customer = selectCustomer();
		if(customer != null) {
			customer.showCustomerLoans(false);
		}
	}
	
	private Customer selectCustomer() {
		System.out.println("Select a customer");
		boolean customersAvailable = showCustomers();
		if (customersAvailable) {
			return customers.get(validateAndReadInput(1, customers.size()) - 1);
		}
		return null;
	}
	
	
	private void loanBookForCustomer() {
		System.out.println("=================");
		System.out.println("Loan a new book");
		Customer customer = selectCustomer();
		if (customer != null) {
			Book book = selectFreeBook();
			if (book != null && !book.isIssued()) {
				loans.add(customer.loanBook(book));
			}
		}
	}

	
	private Book selectFreeBook() {
		System.out.println("Select a free book");
		boolean booksAvailable = showBooks(true);
		if (booksAvailable) {
			return books.get(validateAndReadInput(1, books.size()) - 1);
		}
		return null;
	}
	
	private void customerTypeOptions() {
		int position = 1;
		for (CustomerType customerType : CustomerType.values()) {
			System.out.println(position++ + ". " + customerType.getTypeName());
		}

	}
	
	private int validateAndReadInput(int start, int end) {
		int userInput;
		do {
			userInput = scanner.nextInt();
		} while (userInput < start || userInput > end);
		return userInput;
	}

	public static void main(String[] args) {


		test t1 = new test();
		try {
			t1.startApp();
		} catch (IOException e) {
			System.out.println("Error catched");   // to be red replace err with out;
		}
	}

}

package operations;

import java.time.LocalDateTime;

public class Loan {

	private Customer customer;
	private Book book;
	private LocalDateTime CurrentDate;
	private LocalDateTime expectedReturnDate;
	private LocalDateTime actualReturnDate;
	private boolean returned;
	private boolean finePaid;

	public Loan(Customer customer, Book book) {
		this.customer = customer;
		this.book = book;
		this.CurrentDate = LocalDateTime.now();
		calculateExpectedReturnDate();
		this.finePaid = false;
		this.returned = false;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDateTime getCurrentDate() {
		return CurrentDate;
	}

	public LocalDateTime getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public boolean isFinePaid() {
		return finePaid;
	}

	public void setFinePaid(boolean finePaid) {
		this.finePaid = finePaid;
	}
	
	
	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	private String finePaidStatus() {
		
		if(finePaid)
			return "fined";
		else
			return "Not fined";
	}
	
	private String ReturnedStatus() {
		
		if(returned)
			return "loan returned";
		else
			return"Not returned yet";
		//return returned? "Loan returned" : "Not returned yet";
	}

	public String tostring()
	{
		return "[customer name =" +customer.getName()+" book title=" +book.getTitle()+
				" the current date= " +CurrentDate+
				" paied Stutus="+finePaidStatus()+" the returned Stutus" +ReturnedStatus()+"]";                                       
	}


	public void returnLoan() {
		actualReturnDate = LocalDateTime.now();
		if(actualReturnDate.isAfter(expectedReturnDate)) {
			this.finePaid = true;
		}
		getBook().setIssued(false);
		setReturned(true);
	}
	
	private void calculateExpectedReturnDate() {
		switch (customer.getCustomerType()) {
		case GOLDEN:
			expectedReturnDate = CurrentDate.plusMonths(3);
			break;
		case NORMAL:
			expectedReturnDate =CurrentDate.plusWeeks(3);
			break;
		}
	}
}


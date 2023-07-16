package operations;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Customer {

	private String name;
	private int age;
	private String phoneNumber;
	private CustomerType customerType;
	private double borrowbalance;
	private List<Loan> loans;
	File Cust=new File("Customer.txt");
	BufferedWriter CustOutput = null;
    BufferedReader CustInput = null;
    
    public void writeCustomersInFile(List<Customer> Customers) throws IOException 
    {
	  CustOutput = new BufferedWriter(new FileWriter(Cust));
          for(int i=0;i<Customers.size();i++){
	      CustOutput.write(Customers.get(i).toString()+"\n");
          }
    CustOutput.close();
    }
    
    public void readCustomerFromFile(List<Customer> customers) throws Exception
    {
       String content=null;
     if(Cust.isFile())
         CustInput=new BufferedReader(new FileReader(Cust));
       while ((content=CustInput.readLine())!=null) { 
  String[] CustomersVars = content.split(", "); 
 customers.add(new Customer(CustomersVars[0],Integer.parseInt(CustomersVars[1]),CustomersVars[2],CustomerType.valueOf(CustomersVars[3])));
            }          
       CustInput.close();
    }
    

	public Customer(String name, int age, String phoneNumber,CustomerType customerType) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.customerType = customerType;
		this.borrowbalance = 0;
		this.loans = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public double getBalance() {
		return borrowbalance;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public boolean showCustomerLoans(boolean notReturnedOnly) {
		if (loans.isEmpty()) {
			System.out.println("No loans available!");
			return false;
		}
		int count = 0;
		for (int i = 0; i < loans.size(); i++) {
			if (notReturnedOnly && !loans.get(i).isReturned() || !notReturnedOnly) {
				System.out.println(loans.get(i).toString());
				count++;
			}
		}
		if (count == 0) {
			System.out.println("No not returned loans yet available");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", phoneNumber=" + phoneNumber + ", customerType="
				+ customerType + "]";
	}

	public Loan loanBook(Book book) {
		book.setIssued(true);
		Loan loan = new Loan(this, book);
		this.loans.add(loan);
		return loan;
	}

	public void returnLoan(Loan loan) {
		loan.returnLoan();
		if(loan.isFinePaid()) {
			this.borrowbalance = this.borrowbalance + Book.getFine();
		}
		this.borrowbalance = this.borrowbalance + loan.getBook().getBorrowPrice();
	}

}


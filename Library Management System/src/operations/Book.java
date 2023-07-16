package operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Book {
	
private static final double FINE = 10.0;
	
	private String title;
	private String subject;
	private String author;
	private double borrowPrice;
	private boolean issued;
	
	File Cust=new File("Book.txt");
	BufferedWriter BookOutput = null;
    BufferedReader BookInput = null;
    
    
    public void writeBookInFile(List<Book> Books) throws IOException 
    {
	  BookOutput = new BufferedWriter(new FileWriter(Cust));
          for(int i=0;i<Books.size();i++){
	      BookOutput.write(Books.get(i).toString()+"\n");
          }
    BookOutput.close();
    }
    
    public void readBookFromFile(List<Book> Books) throws Exception
    {
       String content=null;
     if(Cust.isFile())
         BookInput=new BufferedReader(new FileReader(Cust));
       while ((content=BookInput.readLine())!=null) { 
    	   String[] BooksVars = content.split(", "); 
    	   Books.add(new Book(BooksVars[0],BooksVars[1],BooksVars[2],Double.parseDouble(BooksVars[3])));
            }          
       BookInput.close();
    }
	
	// when adding new book
	public Book(String title, String subject, String author, double borrowPrice) {
		this.title = title;
		this.subject = subject;
		this.author = author;
		this.borrowPrice = borrowPrice;
		this.issued = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isIssued() {
		return issued;
	}
	
	private String issuedStatus() {
		return issued? "On Loan" : "Free";
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	public double getBorrowPrice() {
		return borrowPrice;
	}

	public void setBorrowPrice(double borrowPrice) {
		this.borrowPrice = borrowPrice;
	}
	
	public static double getFine() {
		return FINE;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", subject=" + subject + ", author=" + author + ", borrowPrice=" + borrowPrice
				+ ", issuedStatus()=" + issuedStatus() + "]";
	}
}


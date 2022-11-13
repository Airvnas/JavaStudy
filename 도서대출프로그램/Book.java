package my.book;

public class Book{
	String book;
	boolean isRent=false;
	public Book(String book) {
		this.book=book;
		this.isRent=false;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}
}

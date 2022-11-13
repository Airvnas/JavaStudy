package my.book;

public class NoExistBookException extends Exception {
	
	public NoExistBookException(){
		super("NoExistBookException");
	}
	public NoExistBookException(String msg) {
		super(msg);
	}
	
}////////////////////////////////////////////////////////////
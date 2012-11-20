package exceptions;

public class ListNotValidException extends RuntimeException {
	public ListNotValidException() {
		super("List not an adjacency list!");
	}
}

package exceptions;

public class EdgeNotInGraphException extends RuntimeException {

	public EdgeNotInGraphException() {
		super("Edge not in graph!");
	}
}
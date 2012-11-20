package exceptions;

public class MatrixNotValidException extends RuntimeException {
	public MatrixNotValidException() {
		super("Matrix not symmetric with >= 0 entries!");
	}
}


package exceptions;

public class VertexNotInGraphException extends RuntimeException {
	public VertexNotInGraphException() {
		super("Vertex not in graph! Check number of vertices!");
	}
}

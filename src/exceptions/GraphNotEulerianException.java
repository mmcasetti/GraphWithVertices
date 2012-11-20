package exceptions;

public class GraphNotEulerianException extends RuntimeException {
	
	public GraphNotEulerianException() {
		super("Graph not Eulerian, impossible to find a cycle");
	}
	public GraphNotEulerianException(String s) {
		super("Impossible to find a cycle");
	}
}

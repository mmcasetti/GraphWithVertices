package interfaces;

import graphimplementations.Vertex;

public interface EdgeInterface {
	public Vertex getStart();
	public Vertex getEnd();
	public boolean isDirected();
	
	public boolean equals(Object other);
	
	public boolean isLoop();
}
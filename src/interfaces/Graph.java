package interfaces;

import java.util.Set;

import com.google.common.collect.Multiset;

import graphimplementations.Edge;
import graphimplementations.Vertex;

public interface Graph {	
	public Set<Vertex> getVertices();
	public Multiset<Edge> getUndirectedEdges();
	public Multiset<Edge> getDirectedEdges();
	public boolean isDirected();

	public boolean equals(Object obj);
	
	public Graph makeUndirected();
	public Graph makeDirected();
	
	public int getNoOfVertices();
	public Multiset<Edge> getEdgesAt(Vertex vertex);
	public Multiset<Edge> getEdgesFrom(Vertex vertex);
	public Multiset<Edge> getEdgesTo(Vertex vertex);
	public int getDegreeAt(Vertex vertex);
	public int getOutdegreeAt(Vertex vertex);
	public int getIndegreeAt(Vertex vertex);

	public void addVertices(Set<Vertex> newVertices);
	public void removeVertex(Vertex vertex);
	
	public void addUndirectedEdge(Edge edge);
	public void addDirectedEdge(Edge edge);
	public void addUndirectedEdge(Vertex start, Vertex end);
	public void addDirectedEdge(Vertex start, Vertex end);
	public void removeUndirectedEdge(Edge edge);
	public void removeDirectedEdge(Edge edge);
	public void removeUndirectedEdge(Vertex start, Vertex end);
	public void removeDirectedEdge(Vertex start, Vertex end);
}
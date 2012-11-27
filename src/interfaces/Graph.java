package interfaces;

import java.util.Set;

import com.google.common.collect.Multiset;

import graphimplementations.Edge;
import graphimplementations.Vertex;

public interface Graph {	
	public Set<Vertex> getVertices();
	public Multiset<Edge> getEdges();
	public Multiset<Edge> getDirectedEdges();
	public boolean isDirected();

	public boolean equals(Object obj);
	
	public int getNoOfVertices();
	public Multiset<Edge> getEdgesAt(Vertex vertex);
	public Multiset<Edge> getEdgesFrom(Vertex vertex);
	public Multiset<Edge> getEdgesTo(Vertex vertex);
	public int getDegreeAt(Vertex vertex);
	public int getOutdegreeAt(Vertex vertex);
	public int getIndegreeAt(Vertex vertex);

	public void addVertices(Set<Vertex> newVertices);
	public void removeVertex(Vertex vertex);
	
	public void addEdge(Edge edge);
	public void addDirectedEdge(Edge edge);
	public void addEdge(Vertex start, Vertex end);
	public void addDirectedEdge(Vertex start, Vertex end);
	public void removeEdge(Edge edge);
	public void removeDirectedEdge(Edge edge);
	public void removeEdge(Vertex start, Vertex end);
	public void removeDirectedEdge(Vertex start, Vertex end);
}

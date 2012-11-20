package graphimplementations;

import interfaces.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import abstractclasses.AbstractGraph;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement a multigraph, seen as an adjacency list of multisets. A 
 * list of vertices keeps track of the order of the vertices.
 *
 */

public class ListGraph extends AbstractGraph {
	private List<Vertex> vertices;
	private ArrayList<Multiset<Vertex>> adjacencyList;
	private int noOfOneWays;
	
	public ListGraph(List<Vertex> v, ArrayList<Multiset<Vertex>> l) {
		Preconditions.checkArgument(v.size() == l.size(), 
				"Too many or too few vertices");
		
		this.vertices = v;
		this.adjacencyList = l;
		for (Vertex vertex : vertices) {
			int indexOfVertex = getIndexOf(vertex);
			for (Multiset<Vertex> connectedVerticesMulti : adjacencyList) {
				Set<Vertex> connectedVertices = Sets.newHashSet(connectedVerticesMulti);
				for (Vertex representative : connectedVertices) {
					int indexOfConnected = getIndexOf(representative);
					int noOfConnectedInVertexList = adjacencyList.get(indexOfVertex).count(representative);
					int noOfVertexInConnectedList = adjacencyList.get(indexOfConnected).count(vertex);
					if (!representative.equals(vertex) && noOfConnectedInVertexList != noOfVertexInConnectedList) {
						noOfOneWays+=Math.abs(noOfVertexInConnectedList - noOfConnectedInVertexList);
					}					
				}
			}
		}
	}
	
	/**
	 * 	
	 * @param vertex
	 * @return its index in the list of vertices of the graph. Private!
	 */
		private int getIndexOf(Vertex vertex) {
			Preconditions.checkArgument(vertices.contains(vertex), 
					"Vertex not in graph.");		
			return vertices.indexOf(vertex);
		}
	
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			return true;
		}
		if (!(other instanceof Graph)) {
			return false;
		}
		if (!(other instanceof ListGraph)) {
			AbstractGraph otherGraph = (AbstractGraph) other;
			return otherGraph.equals(this);
		} else {
			ListGraph otherListGraph = (ListGraph) other;
			if (this.vertices.size() != otherListGraph.vertices.size()) {
				return false;
			}
			for (int i = 0; i < this.vertices.size(); i++) {
				if (this.adjacencyList.get(i) != 
						otherListGraph.adjacencyList.get(i)) {
					return false;
				}
			}
			return true;
		}
	}
	
	@Override
	public Set<Vertex> getVertices() {
		return Sets.newHashSet(vertices);
	}

	@VisibleForTesting
	List<Vertex> getListOfVertices() {
		return vertices;
	}
	
	@Override
	public Multiset<Edge> getEdges() {
		Multiset<Edge> edges = HashMultiset.create();
		if (!isDirected()) {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex v : adjacencyList.get(i)) {
					if (vertices.indexOf(v) <= i) {
						Edge e = Edge.between(vertices.get(i)).and(v);
						edges.add(e);					
					}
				}
			}			
		} else {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex v : adjacencyList.get(i)) {
					Edge e = Edge.from(vertices.get(i)).to(v);
					edges.add(e);					
				}
			}
		}
		return edges;
	}

	public ArrayList<Multiset<Vertex>> getAdjacencyList() {
		return adjacencyList;
	}	

	@Override
	public boolean isDirected() {
		return (noOfOneWays > 0);
	}
	
	@Override
	public int getNoOfVertices() {
		return vertices.size();
	}

	//TODO this and the next two methods using the list
	@Override
	public Multiset<Edge> getEdgesAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		Multiset<Edge> edgesAt = HashMultiset.create();
		for (Edge e : getEdges()) {
			if ((e.getStart().equals(vertex) || e.getEnd().equals(vertex)) 
					&& !e.isDirected()) {
				edgesAt.add(e);
			}
		}
		return edgesAt;
	}
		
	public Multiset<Edge> getEdgesFrom(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		Multiset<Edge> edgesFrom = HashMultiset.create();
		for (Edge e : getEdges()) {
			if (e.getStart().equals(vertex) && e.isDirected()) {
				edgesFrom.add(e);
			}
		}
		return edgesFrom;
	}
	
	public Multiset<Edge> getEdgesTo(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		Multiset<Edge> edgesTo = HashMultiset.create();
		for (Edge e : getEdges()) {
			if (e.getEnd().equals(vertex) && e.isDirected()) {
				edgesTo.add(e);
			}
		}
		return edgesTo;
	}
	
	@Override
	public int getDegreeAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		return getEdgesAt(vertex).size();
	}
	
	@Override
	public int getOutdegreeAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		return getEdgesFrom(vertex).size();
	}
	
	@Override
	public int getIndegreeAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		return getEdgesTo(vertex).size();
	}
	
	@Override
	public void addVertices(Set<Vertex> newVertices) {
		for (Vertex v : newVertices) {
			Preconditions.checkArgument(!vertices.contains(v), 
					"New vertex already in graph.");
		}
		
		vertices.addAll(newVertices);
		for (int i = 0; i < newVertices.size(); i++) {
			Multiset<Vertex> multiset = HashMultiset.create();
			adjacencyList.add(multiset);
		}
	}

	@Override
	public void removeVertex(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		
		for (int i = 0; i < adjacencyList.size(); i++) {
			adjacencyList.get(i).remove(vertex, 
					adjacencyList.get(i).count(vertex));
		}
		adjacencyList.remove(vertices.indexOf(vertex));
		vertices.remove(vertices.indexOf(vertex));
	}

	@Override
	public void addEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);
		
		adjacencyList.get(startIndex).add(end);
		if (!edge.isLoop() && !edge.isDirected()) {
			adjacencyList.get(endIndex).add(start);			
		}
		if (edge.isDirected()) {
			noOfOneWays++;
		}
	}

	@Override
	public void addEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");		

		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);
		
		adjacencyList.get(startIndex).add(end);
		if (!start.equals(end)) {
			adjacencyList.get(endIndex).add(start);			
		}
	}

	@Override
	public void addArc(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");		

		int startIndex = vertices.indexOf(start);
		
		adjacencyList.get(startIndex).add(end);
		noOfOneWays++;
	}

	@Override
	public void removeEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);

		adjacencyList.get(startIndex).remove(end);
		
		if (!edge.isLoop() && !edge.isDirected()) {
			adjacencyList.get(endIndex).remove(start);
		}
		if (edge.isDirected()) {
			noOfOneWays--;
		}
	}

	@Override
	public void removeEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);

		adjacencyList.get(startIndex).remove(end);
		
		if (!start.equals(end)) {
			adjacencyList.get(endIndex).remove(start);
		}
	}
	
	@Override
	public void removeArc(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");
		
		int startIndex = vertices.indexOf(start);

		adjacencyList.get(startIndex).remove(end);
		noOfOneWays--;
	}

	// TODO directed/undirected
	public boolean isEulerian() {
		
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			boolean evenDegree = true;
			Vertex vertex1 = vertices.get(i);
			for (Vertex vertex2 : getAdjacencyList().get(i)) {
				if (!vertex2.equals(vertex1)) {
					evenDegree = !evenDegree;
				}
			}
			if (!evenDegree) {
				return false;
			}
		}

		return true;
	}
}

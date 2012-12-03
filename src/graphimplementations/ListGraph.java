package graphimplementations;

import interfaces.Graph;

import java.util.List;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import abstractclasses.AbstractGraph;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement a multigraph, seen as an adjacency list of multisets. 
 * A list of vertices keeps track of the order of the vertices. A boolean 
 * keeps track of whether the graph is directed or undirected.
 *
 */

public class ListGraph extends AbstractGraph {
	private List<Vertex> vertices;
	private List<Multiset<Vertex>> adjacencyList;
	private boolean isDirected;
	
	public ListGraph(List<Vertex> vertices, List<Multiset<Vertex>> adjacencyList, boolean isDirected) {
		Preconditions.checkArgument(vertices.size() == adjacencyList.size(), 
				"Too many or too few vertices");
		if (!isDirected) {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex v : adjacencyList.get(i)) {
					int indexV = vertices.indexOf(v);
					Preconditions.checkArgument(adjacencyList.get(i).count(v) == adjacencyList.get(indexV).count(vertices.get(i)));												
				}
			}
		}
			
		this.vertices = vertices;
		this.adjacencyList = adjacencyList;
		this.isDirected = isDirected;
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
			if (otherListGraph.isDirected() != this.isDirected()) {
				return false;
			}
			if (this.getListOfVertices().size() != otherListGraph.getListOfVertices().size()) {
				return false;
			}
			for (int i = 0; i < this.getListOfVertices().size(); i++) {
				if (!this.getAdjacencyList().get(i).equals(otherListGraph.getAdjacencyList().get(i))) {
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
	
	@Override
	public int getNoOfVertices() {
		return vertices.size();
	}

	@VisibleForTesting
	List<Vertex> getListOfVertices() {
		return vertices;
	}

	/**
	 * 	
	 * @param vertex
	 * @return its index in the list of vertices of the graph. Private!
	 */
	private int getIndexOf(Vertex vertex) {
		Preconditions.checkArgument(getListOfVertices().contains(vertex), 
				"Vertex not in graph.");		
		return getListOfVertices().indexOf(vertex);
	}
	
	@Override
	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public Multiset<Edge> getUndirectedEdges() {
		if (isDirected()) {
			return HashMultiset.<Edge>create();
		}

		Multiset<Edge> edges = HashMultiset.create();
		for (int indexStart = 0; indexStart < getAdjacencyList().size(); indexStart++) {
			Vertex start = getListOfVertices().get(indexStart);
			for (Vertex end : getAdjacencyList().get(indexStart)) {
				int indexEnd = getIndexOf(end);
				if (indexStart <= indexEnd) {
					Edge e = Edge.between(start).and(end);
					edges.add(e, getAdjacencyList().get(indexStart).count(end));
				}
			}
		}
		return edges;
	}

	@Override
	public Multiset<Edge> getDirectedEdges() {
		if (!isDirected()) {
			return HashMultiset.<Edge>create();
		}

		Multiset<Edge> edges = HashMultiset.create();
		for (int indexStart = 0; indexStart < getAdjacencyList().size(); indexStart++) {
			Vertex start = getListOfVertices().get(indexStart);
			for (Vertex end : getAdjacencyList().get(indexStart)) {
				Edge e = Edge.from(start).to(end);
				edges.add(e, getAdjacencyList().get(indexStart).count(end));
			}
		}
		return edges;
	}

	
	public List<Multiset<Vertex>> getAdjacencyList() {
		return adjacencyList;
	}	

	@Override
	public Graph makeUndirected() {
		if (!isDirected()) {
			return this;
		}
		List<Multiset<Vertex>> undirectedList = Lists.newArrayList();
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			Multiset<Vertex> listAtI = HashMultiset.create();
			undirectedList.add(listAtI);
			Set<Vertex> setAtI = Sets.newHashSet(getAdjacencyList().get(i));
			for (Vertex v : setAtI) {
				int max = Math.max(getAdjacencyList().get(i).count(v), 
						getAdjacencyList().get(getIndexOf(v)).count(getListOfVertices().get(i)));
				listAtI.add(v, max);
			}
		}
		return new ListGraph(getListOfVertices(), undirectedList, false);
	}

	@Override
	public Graph makeDirected() {
		if (isDirected()) {
			return this;
		}
		return new ListGraph(getListOfVertices(), getAdjacencyList(), true);
	}

	// TODO from here
	
	@Override
	public Multiset<Edge> getEdgesAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		
		Multiset<Edge> edgesAt = HashMultiset.create();		
		int indexStart = getIndexOf(vertex);
		for (Vertex end : adjacencyList.get(indexStart)) {
			int indexEnd = getIndexOf(end);
			if (end.equals(vertex) || 
				adjacencyList.get(indexStart).count(end) == adjacencyList.get(indexEnd).count(vertex)) {
				for (int i = 0; i < adjacencyList.get(indexStart).count(end); i++) {
					Edge e = Edge.between(vertex).and(end);
					edgesAt.add(e);
				}
			} else {
				int m = Math.min(adjacencyList.get(indexStart).count(end), adjacencyList.get(indexEnd).count(vertex));
				for (int i = 0; i < m; i++) {
					Edge e = Edge.between(vertex).and(end);
					edgesAt.add(e);
				}
			}
		}
		return edgesAt;
	}
	
	@Override
	public Multiset<Edge> getEdgesFrom(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");

		Multiset<Edge> edgesFrom = HashMultiset.create();
		int indexV = getIndexOf(vertex);
		for (Vertex w : adjacencyList.get(indexV)) {
			int indexW = getIndexOf(w);
			if (!vertex.equals(w) && 
				adjacencyList.get(indexV).count(w) > adjacencyList.get(indexW).count(vertex)) {
				int m = adjacencyList.get(indexV).count(w) - adjacencyList.get(indexW).count(vertex);
				for (int i = 0; i < m; i++) {
					Edge e = Edge.from(vertex).to(w);
					edgesFrom.add(e);
				}
			}
		}
		return edgesFrom;
	}
	
	// It's not a good idea to do it using the list 
	// - we have to read the whole list anyway
	// with the possible exception of the entry corresponding to the vertex
	@Override
	public Multiset<Edge> getEdgesTo(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		Multiset<Edge> edgesTo = HashMultiset.create();
		for (Edge e : getDirectedEdges()) {
			if (e.getEnd().equals(vertex)) {
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
		
		adjacencyList.remove(vertices.indexOf(vertex));
		for (int i = 0; i < adjacencyList.size(); i++) {
			// remove(elment, occurrences)
			// count(element) - no. of occurrences of element
			adjacencyList.get(i).remove(vertex, 
					adjacencyList.get(i).count(vertex));
		}
		vertices.remove(vertices.indexOf(vertex));
	}

	@Override
	public void addUndirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(!edge.isDirected(), "Use addDirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);
		
		adjacencyList.get(startIndex).add(end);
		if (!edge.isLoop()) {
			adjacencyList.get(endIndex).add(start);			
		}
	}
	
	@Override
	public void addDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(edge.isDirected(), "Use addEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);
		
		adjacencyList.get(startIndex).add(end);
	}
	
	@Override
	public void addUndirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), "Edge's endpoints not in graph.");		

		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);
		
		adjacencyList.get(startIndex).add(end);
		if (!start.equals(end)) {
			adjacencyList.get(endIndex).add(start);			
		}
	}

	@Override
	public void addDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), "Edge's endpoints not in graph.");		

		int startIndex = vertices.indexOf(start);
		
		adjacencyList.get(startIndex).add(end);
	}

	@Override
	public void removeUndirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(!edge.isDirected(), "Use removeDirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);

		adjacencyList.get(startIndex).remove(end);
		
		if (!edge.isLoop()) {
			adjacencyList.get(endIndex).remove(start);
		}
	}

	@Override
	public void removeDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(edge.isDirected(), "Use removeEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = vertices.indexOf(start);

		adjacencyList.get(startIndex).remove(end);
	}

	@Override
	public void removeUndirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		
		int startIndex = vertices.indexOf(start);
		int endIndex = vertices.indexOf(end);

		adjacencyList.get(startIndex).remove(end);
		
		if (!start.equals(end)) {
			adjacencyList.get(endIndex).remove(start);
		}
	}
	
	@Override
	public void removeDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		
		int startIndex = vertices.indexOf(start);

		adjacencyList.get(startIndex).remove(end);
	}

	public boolean isEulerian() {
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			Vertex v = vertices.get(i);
			if (getDegreeAt(v)%2 != 0 || getIndegreeAt(v) != getOutdegreeAt(v)) {
				return false;
			}
		}
		return true;
	}
}

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
//			return (otherGraph.isDirected() == this.isDirected() && 
//					otherGraph.getVertices().equals(this.getVertices()) &&
//					otherGraph.getUndirectedEdges().equals(this.getUndirectedEdges()) && 
//					otherGraph.getDirectedEdges().equals(this.getDirectedEdges()));
		} else {
			ListGraph otherListGraph = (ListGraph) other;
			if (otherListGraph.isDirected() != this.isDirected()) {
				return false;
			}
			if (this.getListOfVertices().size() != otherListGraph.getListOfVertices().size()
				|| this.getAdjacencyList().size() != otherListGraph.getAdjacencyList().size()) {
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
					edges.add(e);
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
				edges.add(e);
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
		return new ListGraph(getListOfVertices(), getAdjacencyList(), false);
	}

	@Override
	public Graph makeDirected() {
		if (isDirected()) {
			return this;
		}
		List<Multiset<Vertex>> directedList = Lists.newArrayList(getAdjacencyList());
		// we have to duplicate the loops - the rest of the list is the same
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			directedList.get(i).add(getListOfVertices().get(i), getAdjacencyList().get(i).count(getListOfVertices().get(i)));
		}
		return new ListGraph(getListOfVertices(), directedList, true);
	}

	@Override
	public Multiset<Edge> getEdgesAt(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(!isDirected(), 
				"Use getEdgesFrom and getEdgesTo.");
		
		Multiset<Edge> edgesAt = HashMultiset.create();		
		int indexOfV = getIndexOf(vertex);
		for (Vertex otherVertex : getAdjacencyList().get(indexOfV)){
			Edge edge = Edge.between(vertex).and(otherVertex);
			edgesAt.add(edge);
		}
		return edgesAt;
	}
	
	@Override
	public Multiset<Edge> getEdgesFrom(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(isDirected(), 
				"Use getEdgesAt.");

		Multiset<Edge> edgesFrom = HashMultiset.create();		
		int indexOfV = getIndexOf(vertex);
		for (Vertex otherVertex : getAdjacencyList().get(indexOfV)){
			Edge edge = Edge.from(vertex).to(otherVertex);
			edgesFrom.add(edge);
		}
		return edgesFrom;
	}
	
	@Override
	public Multiset<Edge> getEdgesTo(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(isDirected(), 
				"Use getEdgesAt.");
		
		Multiset<Edge> edgesTo = HashMultiset.create();
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			Vertex v = getListOfVertices().get(i);
			Edge edge = Edge.from(v).to(vertex);
			edgesTo.add(edge, getAdjacencyList().get(i).count(vertex));
		}
		
		return edgesTo;
	}
	
	@Override
	public int getDegreeAt(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(!isDirected(), 
				"Use getOutdegreeAt and getIndegreeAt");
		
		return getAdjacencyList().get(getIndexOf(vertex)).size();
	}
	
	@Override
	public int getOutdegreeAt(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(isDirected(), 
				"Use getDegreeAt");

		return getAdjacencyList().get(getIndexOf(vertex)).size();
	}
		
	@Override
	public int getIndegreeAt(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		Preconditions.checkArgument(isDirected(), 
				"Use getDegreeAt");

		int indegree = 0;
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			indegree += getAdjacencyList().get(i).count(vertex);
		}
		return indegree;
	}
	
	@Override
	public void addVertices(Set<Vertex> newVertices) {
		for (Vertex v : newVertices) {
			Preconditions.checkArgument(!getVertices().contains(v), 
					"New vertex already in graph.");
		}
		
		getListOfVertices().addAll(newVertices);
		for (int i = 0; i < newVertices.size(); i++) {
			Multiset<Vertex> multiset = HashMultiset.create();
			getAdjacencyList().add(multiset);
		}
	}

	@Override
	public void removeVertex(Vertex vertex) {
		Preconditions.checkArgument(getVertices().contains(vertex), 
				"Vertex not in graph.");
		
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			getAdjacencyList().get(i).remove(vertex, 
					getAdjacencyList().get(i).count(vertex));
		}
		getListOfVertices().remove(getIndexOf(vertex));
		getAdjacencyList().remove(getIndexOf(vertex));
	}

	@Override
	public void addUndirectedEdge(Edge edge) {
		Preconditions.checkArgument(getVertices().contains(edge.getStart()) 
				&& getVertices().contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(!edge.isDirected() && !isDirected(), 
				"Use addDirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = getIndexOf(start);
		int endIndex = getIndexOf(end);
		
		getAdjacencyList().get(startIndex).add(end);
		if (!edge.isLoop()) {
			getAdjacencyList().get(endIndex).add(start);			
		}
	}
	
	@Override
	public void addDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(edge.isDirected() && isDirected(), 
				"Use addUndirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = getIndexOf(start);
		
		getAdjacencyList().get(startIndex).add(end);
	}
	
	@Override
	public void addUndirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(getVertices().contains(start) 
				&& getVertices().contains(end), "Edge's endpoints not in graph.");		
		Preconditions.checkArgument(!isDirected(), "Use addDirectedEdge.");

		int startIndex = getIndexOf(start);
		int endIndex = getIndexOf(end);
		
		getAdjacencyList().get(startIndex).add(end);
		if (!start.equals(end)) {
			getAdjacencyList().get(endIndex).add(start);			
		}
	}

	@Override
	public void addDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(getVertices().contains(start) 
				&& getVertices().contains(end), "Edge's endpoints not in graph.");		
		Preconditions.checkArgument(isDirected(), 
				"Use addUndirectedEdge.");

		int startIndex = getIndexOf(start);
		getAdjacencyList().get(startIndex).add(end);		
	}

	@Override
	public void removeUndirectedEdge(Edge edge) {
		Preconditions.checkArgument(getVertices().contains(edge.getStart()) 
				&& getVertices().contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(!edge.isDirected() && !isDirected(), 
				"Use removeDirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();

		int startIndex = getIndexOf(start);
		int endIndex = getIndexOf(end);

		getAdjacencyList().get(startIndex).remove(end);
		if (!edge.isLoop()) {
			getAdjacencyList().get(endIndex).remove(start);
		}
	}

	@Override
	public void removeDirectedEdge(Edge edge) {
		Preconditions.checkArgument(getVertices().contains(edge.getStart()) 
				&& getVertices().contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getDirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(edge.isDirected() && isDirected(), 
				"Use removeUndirectedEdge.");
		
		Vertex start = edge.getStart();
		Vertex end = edge.getEnd();
		
		int startIndex = getIndexOf(start);

		getAdjacencyList().get(startIndex).remove(end);
	}

	@Override
	public void removeUndirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getUndirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(!isDirected(), "Use removeDirectedEdge.");
		
		int startIndex = getIndexOf(start);
		int endIndex = getIndexOf(end);

		getAdjacencyList().get(startIndex).remove(end);
		if (!start.equals(end)) {
			getAdjacencyList().get(endIndex).remove(start);
		}
	}
	
	@Override
	public void removeDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(getVertices().contains(start) 
				&& getVertices().contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.from(start).to(end);
		Preconditions.checkArgument(getDirectedEdges().contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(isDirected(), "UseRemoveUndirectedEdge.");
		
		int startIndex = getIndexOf(start);

		getAdjacencyList().get(startIndex).remove(end);
	}
	
	public boolean isEulerian() {
		if (!isDirected()) {
			for (Vertex v : getVertices()) {
				if (getDegreeAt(v) % 2 != 0) {
					return false;
				}
			}			
		} else {
			for (Vertex v : getVertices()) {
				if (getIndegreeAt(v) != getOutdegreeAt(v)) {
					return false;
				}
			}
		}
		return true;
	}
}

package graphimplementations;

import interfaces.Graph;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import abstractclasses.AbstractGraph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement a multigraph, seen as an adjacency matrix. 
 * A list of vertices keeps track of the vertices corresponding 
 * to the rows/columns of the matrix independently of their label.
 *
 */

public class MatrixGraph extends AbstractGraph {
	private int[][] adjacencyMatrix;
	private List<Vertex> vertices;
		
	public MatrixGraph(int[][] matrix, List<Vertex> v) {
		Preconditions.checkArgument(matrix.length == v.size(), 
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}
		
		this.adjacencyMatrix = matrix;
		this.vertices = v;
	}

	public MatrixGraph(int[][] matrix) {		
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length != matrix.length,
					"Matrix not square");
		}
		
		this.adjacencyMatrix = matrix;
		this.vertices = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Vertex v = new Vertex();
			vertices.add(v);
		}		
	}

/**
 * @param other
 * @return if the graphs are equal - two matrixgraph are equal iff 
 * their matrices are equal	
 */
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			return true;
		}
		if (!(other instanceof Graph)) {
			return false;
		}
		if (!(other instanceof MatrixGraph)) {
			AbstractGraph otherGraph = (AbstractGraph) other;
			return otherGraph.equals(this);
		} else {
			MatrixGraph otherMatrixGraph = (MatrixGraph) other;
			return Arrays.deepEquals(otherMatrixGraph.getMatrix(), 
					this.getMatrix()); 
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

	@Override
	public boolean isDirected() {
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < i; j++) {
				if (getMatrix()[i][j] != getMatrix()[j][i]) {
					return true;
				}
			}
		}
		return false;
	}	

	@Override
	public Multiset<Edge> getEdges() {
		Multiset<Edge> edges = HashMultiset.create();
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k < Math.min(adjacencyMatrix[i][j], adjacencyMatrix[j][i]); k++) {
					Vertex start = vertices.get(i);
					Vertex end = vertices.get(j);
					Edge edge = Edge.between(start).and(end);
					edges.add(edge);					
				}
			}
		}			
		return edges;
	}

	@Override
	public Multiset<Edge> getDirectedEdges() {
		Multiset<Edge> edges = HashMultiset.create();
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k < Math.abs(adjacencyMatrix[i][j] - adjacencyMatrix[j][i]); k++) {
					Vertex start;
					Vertex end;
					if (adjacencyMatrix[i][j] > adjacencyMatrix[j][i]) {
						start = vertices.get(i);
						end = vertices.get(j);						
					} else {
					// since we are iterating on k = 0; 
					// k < Math.abs(matrix[i][j] - matrix[j][i])
					// we are excluding the case in which matrix[i][j] = matrix[j][i]
						start = vertices.get(j);
						end = vertices.get(i);												
					}
					Edge edge = Edge.between(start).and(end);
					edges.add(edge);					
				}
			}
		}			
		return edges;
	}
	
	public int[][] getMatrix() {
		return adjacencyMatrix;
	}
	
	@Override
	public Multiset<Edge> getEdgesAt(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");

		int indexOfVertex = getIndexOf(vertex);
		Multiset<Edge> edgesAt = HashMultiset.create();
		for (int i = 0; i < getNoOfVertices(); i++) {
			if (adjacencyMatrix[indexOfVertex][i] == adjacencyMatrix[i][indexOfVertex]) {
				for (int k = 0; k < adjacencyMatrix[indexOfVertex][i]; k++) {
					Edge edge = Edge.between(vertex).and(vertices.get(i));
					edgesAt.add(edge);
				}
			}
		}
		return edgesAt;
	}
		
	public Multiset<Edge> getEdgesFrom(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");

		int indexOfVertex = getIndexOf(vertex);
		Multiset<Edge> edgesFrom = HashMultiset.create();
		for (int i = 0; i < getNoOfVertices(); i++) {
			if (adjacencyMatrix[indexOfVertex][i] > adjacencyMatrix[i][indexOfVertex]) {
				for (int k = 0; k < adjacencyMatrix[indexOfVertex][i]; k++) {
					Edge edge = Edge.from(vertex).to(vertices.get(i));
					edgesFrom.add(edge);
				}
			}
		}
		return edgesFrom;
	}
	
	public Multiset<Edge> getEdgesTo(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");

		int indexOfVertex = getIndexOf(vertex);
		Multiset<Edge> edgesTo = HashMultiset.create();
		for (int i = 0; i < getNoOfVertices(); i++) {
			if (adjacencyMatrix[indexOfVertex][i] < adjacencyMatrix[i][indexOfVertex]) {
				for (int k = 0; k < adjacencyMatrix[indexOfVertex][i]; k++) {
					Edge edge = Edge.from(vertices.get(i)).to(vertex);
					edgesTo.add(edge);
				}
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
	public void addVertices(Set<Vertex> newVertices) {
		for (Vertex v : newVertices) {
			Preconditions.checkArgument(!vertices.contains(v), 
					"New vertex already in graph.");
		}
		
		int newNoOfVertices = getNoOfVertices() + newVertices.size();
		
		int[][] newMatrix = new int[newNoOfVertices][newNoOfVertices];		
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < getNoOfVertices(); j++) {
				newMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		adjacencyMatrix = newMatrix;
		vertices.addAll(newVertices);
	}

	@Override
	public void removeVertex(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");
		
		List<Vertex> newVertices = Lists.newArrayList();
		for (Vertex v : vertices) {
			if (!v.equals(vertex)) {
				newVertices.add(v);
			}
		}
		int index = getIndexOf(vertex);
		int newNoOfVertices = getNoOfVertices() - 1;
		int[][] newMatrix = new int[newNoOfVertices][newNoOfVertices];
		
		for (int i = 0; i < index; i++) {
			for (int j = 0; j < index; j++) {
				newMatrix[i][j] = adjacencyMatrix[i][j];				
			}
		}
		for (int i = index; i < newNoOfVertices; i++) {
			for (int j = index; j < newNoOfVertices; j++) {
				newMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
			}
		}
		adjacencyMatrix = newMatrix;
		vertices = newVertices;
	}

	@Override
	public void addEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(!edge.isDirected(), "Use addDirectedEdge.");
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]++;
		if (!edge.getStart().equals(edge.getEnd())) {
			adjacencyMatrix[endLabel][startLabel]++;			
		}
	}
	
	@Override
	public void addDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(edge.isDirected(), "Use addEdge.");
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]++;		
	}
	
	@Override
	public void addEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), "Edge's endpoints not in graph.");

		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]++;
		if (!start.equals(end)) {
			adjacencyMatrix[endLabel][startLabel]++;			
		}
	}

	@Override
	public void addDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");

		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]++;
	}
	
	@Override
	public void removeEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");		
		Preconditions.checkArgument(!edge.isDirected(), "Use addDirectedEdge.");
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]--;
		if (!edge.getStart().equals(edge.getEnd())) {
			adjacencyMatrix[endLabel][startLabel]--;
		}
	}

	@Override
	public void removeDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");		
		Preconditions.checkArgument(edge.isDirected(), "Use removeEdge.");
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]--;
	}
	
	@Override
	public void removeEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");
		
		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]--;
		if (!start.equals(end)) {
			adjacencyMatrix[endLabel][startLabel]--;			
		}
	}

	@Override
	public void removeDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");
		
		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]--;
	}
		
	public boolean isEulerian() {		
		for (int v = 0; v < getNoOfVertices(); v++){
			int undirected = 0;
			int out = 0;
			int in = 0;
			for (int j = 0; j < getNoOfVertices(); j++){
				if (j != v) {
					undirected += Math.min(adjacencyMatrix[v][j], adjacencyMatrix[j][v]);					
					if (adjacencyMatrix[v][j] < adjacencyMatrix[j][v]) {
						in += (adjacencyMatrix[j][v] - adjacencyMatrix[v][j]);
					} else if (adjacencyMatrix[v][j] > adjacencyMatrix[j][v]) {
						out += (adjacencyMatrix[v][j] - adjacencyMatrix[j][v]);
					}
				}
			}
			if (undirected%2 == 1 || (in - out) != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isPerfectMatching(int[][] dirSubset) {
		Preconditions.checkArgument(dirSubset.length == vertices.size(), 
				"Not enough or too many vertices in subset");
		// precondition: subset is subset of edges 
		for (int i = 0; i < dirSubset.length; i++) {
			for (int j = 0; j < dirSubset.length; j++) {
				Preconditions.checkArgument(dirSubset[i][j] <= adjacencyMatrix[i][j],
						"Not a subset of edges");
			}
		}
		
		// if we have a directed edge (that is, an entry +1 in [a][b])
		// we want to consider it as an undirected edge (+1 in [a][b] and +1 in [b][a])
		int[][] subset = new int[dirSubset.length][dirSubset.length];
		for (int i = 0; i < subset.length; i++) {
			for (int j = 0; j < subset.length; j++) {
				subset[i][j] = Math.max(dirSubset[i][j], dirSubset[j][i]);
			}
		}
						
		for (int v = 0; v < vertices.size(); v++){
			// no loops
			if (subset[v][v] > 0){
				return false;
			}
			// each vertex covered exactly once
			else {
				int rowSum = 0;
				for (int j = 0; j < vertices.size(); j++){
					rowSum += subset[v][j];
					if (rowSum > 1) {
						return false;
					}
				}
			}
		}
		return true;
	}
}

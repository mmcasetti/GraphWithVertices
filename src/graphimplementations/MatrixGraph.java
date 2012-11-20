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

import exceptions.EdgeNotInGraphException;
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
	private int noOfOneWays;
		
	public MatrixGraph(int[][] matrix, List<Vertex> v) {
		Preconditions.checkArgument(matrix.length == v.size(), 
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}
		
		this.adjacencyMatrix = matrix;
		this.vertices = v;
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < i; j++) {
				if (getMatrix()[i][j] != getMatrix()[j][i]) {
					int oneWaysAtij = Math.abs(getMatrix()[i][j] 
							- getMatrix()[j][i]);
					this.noOfOneWays+=oneWaysAtij;
				}
			}
		}
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
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < i; j++) {
				if (getMatrix()[i][j] != getMatrix()[j][i]) {
					int oneWaysAtij = Math.abs(getMatrix()[i][j] 
							- getMatrix()[j][i]);
					this.noOfOneWays+=oneWaysAtij;
				}
			}
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
	public Multiset<Edge> getEdges() {
		Multiset<Edge> edges = HashMultiset.create();
		if (!isDirected()) {
			for (int i = 0; i < getNoOfVertices(); i++) {
				for (int j = 0; j <= i; j++) {
					for (int k = 0; k < adjacencyMatrix[i][j]; k++) {
						Vertex start = vertices.get(i);
						Vertex end = vertices.get(j);
						Edge edge = Edge.between(start).and(end);
						edges.add(edge);					
					}
				}
			}			
		} else {
			for (int i = 0; i < getNoOfVertices(); i++) {
				for (int j = 0; j <= getNoOfVertices(); j++) {
					for (int k = 0; k < adjacencyMatrix[i][j]; k++) {
						Vertex start = vertices.get(i);
						Vertex end = vertices.get(j);
						Edge edge = Edge.from(start).to(end);
						edges.add(edge);					
					}
				}
			}
		}
		return edges;
	}

	@Override
	public boolean isDirected() {
//		for (int i = 0; i < getNoOfVertices(); i++) {
//			for (int j = 0; j < i; j++) {
//				if (getMatrix()[i][j] != getMatrix()[j][i]) {
//					return true;
//				}
//			}
//		}
//		return false;
		return (noOfOneWays > 0);
	}	
	
	public int[][] getMatrix() {
		return adjacencyMatrix;
	}
	
	//TODO this and the next two methods using the matrix
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
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]++;
		if (!edge.getStart().equals(edge.getEnd()) && !edge.isDirected()) {
			adjacencyMatrix[endLabel][startLabel]++;			
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

		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]++;
		if (!start.equals(end)) {
			adjacencyMatrix[endLabel][startLabel]++;			
		}
	}

	@Override
	public void addArc(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");

		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]++;
		noOfOneWays++;
	}
	
	@Override
	public void removeEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(getEdges().contains(edge), 
				"Edge not in graph.");		
		
		int startLabel = getIndexOf(edge.getStart());
		int endLabel = getIndexOf(edge.getEnd());
		
		adjacencyMatrix[startLabel][endLabel]--;
		if (!edge.getStart().equals(edge.getEnd()) && !edge.isDirected()) {
			adjacencyMatrix[endLabel][startLabel]--;
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
		
		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]--;
		if (!start.equals(end)) {
			adjacencyMatrix[endLabel][startLabel]--;			
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
		
		int startLabel = getIndexOf(start);
		int endLabel = getIndexOf(end);
		
		adjacencyMatrix[startLabel][endLabel]--;
		noOfOneWays--;
	}
		
	// TODO directed/undirected
	public boolean isEulerian() {		
		for (int i = 0; i < getNoOfVertices(); i++){
			// each vertex covered exactly once
			int rowSum = 0;
			for (int j = 0; j < getNoOfVertices(); j++){
				if (j != i) {
					rowSum = rowSum + getMatrix()[i][j];
				}
			}
			if (rowSum%2 == 1) {
				return false;
			}
		}
		return true;
	}

	public boolean isPerfectMatching(int[][] subset) {
		Preconditions.checkArgument(subset.length == adjacencyMatrix.length, 
				"Not enough or too many vertices in subset");
		// precondition: subset is subset of edges 
		// => every entry in subset is a nonzero entry in the graph's matrix
		for (int i = 0; i < subset.length; i++) {
			for (int j = 0; j < subset.length; j++) {
				Preconditions.checkArgument(!(subset[i][j] != 0 && adjacencyMatrix[i][j] == 0),
						"Not a subset of edges");
			}
		}
		
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < getNoOfVertices(); j++) {
				if (subset[i][j] >= 1 && getMatrix()[i][j] == 0) {
					throw new EdgeNotInGraphException();
				}				
			}
		}
		
		for (int i = 0; i < getNoOfVertices(); i++){
			// no loops
			if (subset[i][i] > 0){
				return false;
			}
			// each vertex covered exactly once
			else {
				int rowSum = 0;
				for (int j = 0; j < getNoOfVertices(); j++){
					rowSum = rowSum + subset[i][j];
				}
				if (rowSum != 1) {
					return false;
				}
			}
		}
		return true;
	}
}

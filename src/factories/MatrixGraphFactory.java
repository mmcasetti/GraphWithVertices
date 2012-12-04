package factories;

import graphimplementations.Edge;
import graphimplementations.MatrixGraph;
import graphimplementations.Vertex;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Set;

public class MatrixGraphFactory {
		
	public MatrixGraph createMatrixGraph(List<Vertex> vertices, int[][] matrix, boolean isDirected) {
		Preconditions.checkArgument(matrix.length == vertices.size(),
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
			if (!isDirected) {
				for (int j = 0; j < matrix.length; j++) {
					Preconditions.checkArgument(matrix[i][j] == matrix[j][i],
							"Matrix not symmetric in undirected graph");
				}
			}
		}
		
		return new MatrixGraph(vertices, matrix, isDirected);
	}

	public MatrixGraph createMatrixGraph(int[][] matrix, boolean isDirected) {
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
			if (!isDirected) {
				for (int j = 0; j < matrix.length; j++) {
					Preconditions.checkArgument(matrix[i][j] == matrix[j][i],
							"Matrix not symmetric in undirected graph");
				}
			}
		}		
		
		List<Vertex> vertices = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Vertex v = new Vertex();
			vertices.add(v);
		}
		
		return new MatrixGraph(vertices, matrix, isDirected);
	}

	public MatrixGraph createMatrixGraph(Set<Vertex> vertices, Multiset<Edge> undirectedEdges, Multiset<Edge> directedEdges) {
		if (!undirectedEdges.isEmpty()) {
			Preconditions.checkArgument(directedEdges.isEmpty(), "Choose between directed and undirected graph.");
		}
		if (!directedEdges.isEmpty()) {
			Preconditions.checkArgument(undirectedEdges.isEmpty(), "Choose between directed and undirected graph.");
		}
		for (Edge edge : undirectedEdges) {
			Preconditions.checkArgument(vertices.contains(edge.getStart()) && vertices.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}
		for (Edge edge : directedEdges) {
			Preconditions.checkArgument(vertices.contains(edge.getStart()) && vertices.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}
		for (Edge edge : undirectedEdges) {
			Preconditions.checkArgument(!edge.isDirected(),
					"Directed edge in edges");
		}
		for (Edge edge : directedEdges) {
			Preconditions.checkArgument(edge.isDirected(),
					"Nondirected edge in directed edges");
		}

		int[][] matrix = new int[vertices.size()][vertices.size()];
		List<Vertex> listOfVertices = Lists.newArrayList(vertices);		
		boolean isDirected = undirectedEdges.isEmpty();

		if (!isDirected) {
			for (Edge e : undirectedEdges) {
				int start = listOfVertices.indexOf(e.getStart());
				int end = listOfVertices.indexOf(e.getEnd());
				
				matrix[start][end]++;
				if (start != end) {
					matrix[end][start]++;
				}
			}			
		} else {
			for (Edge e : directedEdges) {
				int start = listOfVertices.indexOf(e.getStart());
				int end = listOfVertices.indexOf(e.getEnd());
				
				matrix[start][end]++;
			}
		}
		
		return new MatrixGraph(listOfVertices, matrix, isDirected);
	}

	public MatrixGraph createMatrixGraph(List<Vertex> vertices, List<Multiset<Vertex>> adjacencyList, boolean isDirected) {
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
		
		int[][] adjacencyMatrix = new int[vertices.size()][vertices.size()];

		if (!isDirected) {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex vertex : adjacencyList.get(i)) {
					int indexOfVertex = vertices.indexOf(vertex);
					if (indexOfVertex <= i) {
						adjacencyMatrix[i][indexOfVertex]++;
						if (i != indexOfVertex) {
							adjacencyMatrix[indexOfVertex][i]++;
						}					
					}
				}
			}			
		} else {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex vertex : adjacencyList.get(i)) {
					adjacencyMatrix[i][vertices.indexOf(vertex)]++;
				}
			}
		}
		
		return new MatrixGraph(vertices, adjacencyMatrix, isDirected);
	}
}

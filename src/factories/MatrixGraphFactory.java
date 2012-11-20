package factories;

import graphimplementations.Edge;
import graphimplementations.MatrixGraph;
import graphimplementations.Vertex;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


// TODO preconditions
// TODO directed graphs

public class MatrixGraphFactory {
		
	public MatrixGraph createMatrixGraph(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}		
		
		List<Vertex> vertices = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Vertex v = new Vertex();
			vertices.add(v);
		}
		return new MatrixGraph(matrix, vertices);
	}

	public MatrixGraph createMatrixGraph(int[][] matrix, List<Vertex> vertices) {
		Preconditions.checkArgument(matrix.length == vertices.size(),
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}
		
		return new MatrixGraph(matrix, vertices);
	}

	public MatrixGraph createMatrixGraph(Set<Vertex> vertices, Multiset<Edge> edges) {
		for (Edge edge : edges) {
			Preconditions.checkArgument(vertices.contains(edge.getStart()) && vertices.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}
		
		int[][] matrix = new int[vertices.size()][vertices.size()];
		
		List<Vertex> listOfVertices = Lists.newArrayList(vertices);		
		
		for (Edge e : edges) {
			int start = listOfVertices.indexOf(e.getStart());
			int end = listOfVertices.indexOf(e.getEnd());
			
			matrix[start][end]++;
			if (start != end) {
				matrix[end][start]++;
			}
		}
		
		return new MatrixGraph(matrix, listOfVertices);
	}

	public MatrixGraph createMatrixGraph(List<Vertex> vertices, ArrayList<Multiset<Vertex>> adjacencyList) {
		Preconditions.checkArgument(vertices.size() == adjacencyList.size(),
				"Too many or too few vertices");

		int[][] adjacencyMatrix = new int[vertices.size()][vertices.size()];
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
		return new MatrixGraph(adjacencyMatrix, vertices);
	}
}

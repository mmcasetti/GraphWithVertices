package factories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import graphimplementations.Edge;
import graphimplementations.ListGraph;
import graphimplementations.Vertex;

// TODO preconditions
// TODO directed graphs

public class ListGraphFactory {
	
	public ListGraph createListGraph(List<Vertex> vertices, ArrayList<Multiset<Vertex>> adjacencyList) {
		Preconditions.checkArgument(vertices.size() == adjacencyList.size(),
				"Too many or too few vertices");

		return new ListGraph(vertices, adjacencyList);
	}

	public ListGraph createListGraph(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}

		List<Vertex> vertices = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Vertex v = new Vertex();
			vertices.add(v);
		}
		
		ArrayList<Multiset<Vertex>> adjacencyList = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Multiset<Vertex> multiset = HashMultiset.create();
			adjacencyList.add(multiset);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				for (int k = 0; k < matrix[i][j]; k++) {
					adjacencyList.get(i).add(vertices.get(j));
				}
			}
		}
		return new ListGraph(vertices, adjacencyList);
	}

	public ListGraph createListGraph(int[][] matrix, List<Vertex> vertices) {		
		Preconditions.checkArgument(matrix.length == vertices.size(),
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}

		ArrayList<Multiset<Vertex>> adjacencyList = Lists.newArrayList();
		for (int i = 0; i < matrix.length; i++) {
			Multiset<Vertex> multiset = HashMultiset.create();
			adjacencyList.add(multiset);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				for (int k = 0; k < matrix[i][j]; k++) {
					adjacencyList.get(i).add(vertices.get(j));
				}
			}
		}
		return new ListGraph(vertices, adjacencyList);
	}
	
	public ListGraph createListGraph(Set<Vertex> vertices, Multiset<Edge> edges) {
		for (Edge edge : edges) {
			Preconditions.checkArgument(vertices.contains(edge.getStart()) && vertices.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}

		List<Vertex> verticesList = Lists.newArrayList();
		for (Vertex v : vertices) {
			verticesList.add(v);
		}
		ArrayList<Multiset<Vertex>> adjacencyList = Lists.newArrayList();
		for (Vertex vertex : verticesList) {
			Multiset<Vertex> multiset = HashMultiset.create();
			adjacencyList.add(multiset);
			for (Edge edge : edges) {
				if (edge.getStart().equals(vertex)) {
					multiset.add(edge.getEnd());
				}
				if (edge.getEnd().equals(vertex) && !edge.getEnd().equals(edge.getStart())) {
					multiset.add(edge.getStart());
				}
			}
		}
		return new ListGraph(verticesList, adjacencyList);
	}
}
package factories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graphimplementations.Edge;
import graphimplementations.EdgesGraph;
import graphimplementations.Vertex;


import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Multiset;


// TODO preconditions
// TODO directed graphs

public class EdgesGraphFactory {
	
	public EdgesGraph createEdgesGraph(Set<Vertex> vertices, Multiset<Edge> edges) {
		for (Edge edge : edges) {
			Preconditions.checkArgument(vertices.contains(edge.getStart()) && vertices.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}

		return new EdgesGraph(vertices, edges);
	}

	public EdgesGraph createEdgesGraph(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}
		
		List<Vertex> vertices = Lists.newArrayList();
		Multiset<Edge> edges = HashMultiset.create();
		
		for (int i = 0; i < matrix.length; i++) { 
			Vertex v = new Vertex();
			vertices.add(v);
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j <= i; j++) {
				Vertex start = vertices.get(i);
				Vertex end = vertices.get(j);
				Edge edge = Edge.between(start).and(end);
				edges.add(edge, matrix[i][j]);
			}
		}

		Set<Vertex> verticesSet = Sets.newHashSet(vertices);
		return new EdgesGraph(verticesSet, edges);
	}

	public EdgesGraph createEdgesGraph(int[][] matrix, List<Vertex> vertices) {
		Preconditions.checkArgument(matrix.length == vertices.size(),
				"Too many or too few vertices");
		for (int i = 0; i < matrix.length; i++) {
			Preconditions.checkArgument(matrix[i].length == matrix.length,
					"Matrix not square");
		}
		
		Multiset<Edge> edges = HashMultiset.create();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j <= i; j++) {
				Vertex start = vertices.get(i);
				Vertex end = vertices.get(j);
				Edge edge = Edge.between(start).and(end);
				edges.add(edge, matrix[i][j]);
			}
		}

		Set<Vertex> verticesSet = Sets.newHashSet(vertices);
		return new EdgesGraph(verticesSet, edges);
	}

	
	public EdgesGraph createEdgesGraph(List<Vertex> vertices, ArrayList<Multiset<Vertex>> adjacencyList) {
		Preconditions.checkArgument(vertices.size() == adjacencyList.size(),
				"Too many or too few vertices");

		Set<Vertex> verticesSet = Sets.newHashSet(vertices);
		Multiset<Edge> edges = HashMultiset.create();
		for (int i = 0; i < adjacencyList.size(); i++) {
			for (Vertex vertex : adjacencyList.get(i)) {
				if (vertices.indexOf(vertex) <= i) {
					Vertex v = vertices.get(i);
					Edge edge = Edge.between(vertex).and(v);
					edges.add(edge);					
				}
			}
		}
		
		return new EdgesGraph(verticesSet, edges);
	}	
}

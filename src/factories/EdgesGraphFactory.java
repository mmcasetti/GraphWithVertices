package factories;

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


public class EdgesGraphFactory {
	
	public EdgesGraph createEdgesGraph(Set<Vertex> vertices, Multiset<Edge> undirectedEdges, Multiset<Edge> directedEdges) {
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

		return new EdgesGraph(vertices, undirectedEdges, directedEdges);
	}

	public EdgesGraph createEdgesGraph(int[][] matrix, boolean isDirected) {
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
		
		Set<Vertex> verticesSet = Sets.newHashSet(vertices);		

		Multiset<Edge> undirectedEdges = HashMultiset.create();
		Multiset<Edge> directedEdges = HashMultiset.create();

		if (!isDirected) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j <= i; j++) {
					Edge edge = Edge.between(vertices.get(i)).and(vertices.get(j));
					undirectedEdges.add(edge, matrix[i][j]);
				}
			}
		} else {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					Edge edge = Edge.from(vertices.get(i)).to(vertices.get(j));
					undirectedEdges.add(edge, matrix[i][j]);
				}
			}
		}

		return new EdgesGraph(verticesSet, undirectedEdges, directedEdges);
	}

	public EdgesGraph createEdgesGraph(List<Vertex> vertices, int[][] matrix, boolean isDirected) {
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

		Set<Vertex> verticesSet = Sets.newHashSet(vertices);		
		
		Multiset<Edge> undirectedEdges = HashMultiset.create();
		Multiset<Edge> directedEdges = HashMultiset.create();

		if (!isDirected) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j <= i; j++) {
					Edge edge = Edge.between(vertices.get(i)).and(vertices.get(j));
					undirectedEdges.add(edge, matrix[i][j]);
				}
			}
		} else {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					Edge edge = Edge.from(vertices.get(i)).to(vertices.get(j));
					undirectedEdges.add(edge, matrix[i][j]);
				}
			}
		}

		return new EdgesGraph(verticesSet, undirectedEdges, directedEdges);
	}

	public EdgesGraph createEdgesGraph(List<Vertex> vertices, List<Multiset<Vertex>> adjacencyList, boolean isDirected) {
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
		
		Set<Vertex> verticesSet = Sets.newHashSet(vertices);

		Multiset<Edge> undirectedEdges = HashMultiset.create();
		Multiset<Edge> directedEdges = HashMultiset.create();

		if (!isDirected) {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex vertex : adjacencyList.get(i)) {
					if (vertices.indexOf(vertex) <= i) {
						Vertex otherVertex = vertices.get(i);
						Edge edge = Edge.between(vertex).and(otherVertex);
						undirectedEdges.add(edge);					
					}
				}
			}			
		} else {
			for (int i = 0; i < adjacencyList.size(); i++) {
				for (Vertex vertex : adjacencyList.get(i)) {
					Vertex otherVertex = vertices.get(i);
					Edge edge = Edge.from(vertex).to(otherVertex);
					undirectedEdges.add(edge);					
				}
			}			
		}
		
		return new EdgesGraph(verticesSet, undirectedEdges, directedEdges);
	}	
}
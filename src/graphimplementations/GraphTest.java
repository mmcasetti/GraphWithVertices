package graphimplementations;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Sets;

import factories.EdgesGraphFactory;
import factories.ListGraphFactory;
import factories.MatrixGraphFactory;
import graphimplementations.EdgesGraph;
import graphimplementations.MatrixGraph;
import graphimplementations.Edge;
import graphimplementations.Vertex;

public class GraphTest {
	public Vertex v1 = new Vertex();
	public Vertex v2 = new Vertex();
	public Vertex v3 = new Vertex();
	public Set<Vertex> verticesSet = Sets.newHashSet(v1, v2, v3);
	public List<Vertex> verticesList = Lists.newArrayList(v1, v2, v3);
	
	public Edge e1u = Edge.between(v1).and(v2);
	public Edge e2u = Edge.between(v2).and(v3);
	public Edge e3u = Edge.between(v3).and(v1);
	public Edge undirectedLoop = Edge.between(v1).and(v1);
	public Multiset<Edge> undirectedEdges = HashMultiset.create();
	@Before
	public void initializeUndirectedEdges() {
		undirectedEdges.add(e1u);
		undirectedEdges.add(e1u);
		undirectedEdges.add(e2u);
		undirectedEdges.add(e3u);
		undirectedEdges.add(undirectedLoop);
	}
	
	public Edge e1d = Edge.from(v1).to(v2);
	public Edge e2d = Edge.from(v2).to(v3);
	public Edge e3d = Edge.from(v3).to(v1);
	public Edge directedLoop = Edge.from(v1).to(v1);
	public Multiset<Edge> directedEdges1 = HashMultiset.create();
	@Before
	public void initializeDirectedEdges1() {
		directedEdges1.add(e1d);
		directedEdges1.add(e1d);
		directedEdges1.add(e2d);
		directedEdges1.add(e3d);
		directedEdges1.add(directedLoop);
	}	
	public Edge e1dOpposite = Edge.from(v2).to(v1);
	public Edge e2dOpposite = Edge.from(v3).to(v2);
	public Edge e3dOpposite = Edge.from(v1).to(v3);
	public Multiset<Edge> directedEdges2 = HashMultiset.create();
	@Before
	public void initializeDirectedEdges2() {
		directedEdges2.add(e1d);
		directedEdges2.add(e1d);
		directedEdges2.add(e1dOpposite);
		directedEdges2.add(e1dOpposite);
		directedEdges2.add(e2d);
		directedEdges2.add(e2dOpposite);
		directedEdges2.add(e3d);
		directedEdges2.add(e3dOpposite);
		directedEdges2.add(directedLoop);
		directedEdges2.add(directedLoop);
	}
	public Multiset<Edge> directedEdges3 = HashMultiset.create();
	@Before
	public void initializeDirectedEdges3() {
		directedEdges3.add(e1d);
		directedEdges3.add(e1d);
		directedEdges3.add(e1dOpposite);
		directedEdges3.add(e1dOpposite);
		directedEdges3.add(e2d);
		directedEdges3.add(e2dOpposite);
		directedEdges3.add(e3d);
		directedEdges3.add(e3dOpposite);
		directedEdges3.add(directedLoop);
	}
	
	public int[][] matrix1 = {{ 1, 2, 1 },
							  { 2, 0, 1 },
							  { 1, 1, 0 }};
	public int[][] matrix2 = {{ 2, 4, 2 },
			 				  { 4, 0, 2 },
			 				  { 2, 2, 0 }};
	
	public List<Multiset<Vertex>> list1 = Lists.newArrayList();
	@Before
	public void initializeList1() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list1.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		list1.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		list1.add(multiset3);
	}
	public List<Multiset<Vertex>> list2 = Lists.newArrayList();
	@Before
	public void initializeList2() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list2.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		list2.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		list2.add(multiset3);
	}
	
	
	// Factories, equals
	public EdgesGraphFactory edgesGraphFactory = new EdgesGraphFactory();
	public MatrixGraphFactory matrixGraphFactory = new MatrixGraphFactory();
	public ListGraphFactory listGraphFactory = new ListGraphFactory();
	
	@Test
	public void edgesGraph_edges_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void edgesGraph_edges_directed_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_edges_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_edges_directed_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_edges_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_edges_directed_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void edgesGraph_matrix_1_factory_true() {		
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(matrix1, false);

		assertEquals(graph, graph);
	}
		
	@Test
	public void edgesGraph_matrix_1_directed_factory_true() {		
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(matrix1, true);

		assertEquals(graph, graph);
	}
		
	@Test
	public void edgesGraph_matrix_2_factory_true() {		
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);

		assertEquals(graph, graph);
	}
	
	@Test
	public void edgesGraph_matrix_2_directed_factory_true() {		
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, true);

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_matrix_1_factory_true() {		
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(matrix1, false);

		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_matrix_1_directed_factory_true() {		
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(matrix1, true);

		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_matrix_2_factory_true() {		
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_matrix_2_directed_factory_true() {		
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_matrix_1_factory_true() {		
		ListGraph graph = listGraphFactory.createListGraph(matrix1, false);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_matrix_1_directed_factory_true() {		
		ListGraph graph = listGraphFactory.createListGraph(matrix1, true);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_matrix_2_factory_true() {		
		ListGraph graph = listGraphFactory.createListGraph(verticesList, matrix1, false);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_matrix_2_directed_factory_true() {		
		ListGraph graph = listGraphFactory.createListGraph(verticesList, matrix1, true);

		assertEquals(graph, graph);
	}
	
	@Test
	public void edgesGraph_list_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, list1, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void edgesGraph_list_directed_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, list1, true);
		
		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_list_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_list_directed_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, true);
		
		assertEquals(graph, graph);
	}

	@Test
	public void listGraph_list_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void listGraph_list_directed_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, true);
		
		assertEquals(graph, graph);
	}

	// Factories, equals - same graph no matter if built via edges & vertices, matrix, list
	
	@Test
	public void edgesGraph_edges_matrix_factory_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		EdgesGraph graphMatrix = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);
		
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void edgesGraph_edges_list_factory_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		EdgesGraph graphList = edgesGraphFactory.createEdgesGraph(verticesList, list1, false);
		
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void edgesGraph_matrix_list_factory_true() {
		EdgesGraph graphMatrix = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);
		EdgesGraph graphList = edgesGraphFactory.createEdgesGraph(verticesList, list1, false);
		
		assertEquals(graphMatrix, graphList);
	}
	
	@Test
	public void matrixGraph_edges_matrix_factory_true() {
		MatrixGraph graphEdges = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void matrixGraph_edges_list_factory_true() {
		MatrixGraph graphEdges = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		MatrixGraph graphList = matrixGraphFactory.createMatrixGraph(verticesList, list1, false);
		
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void matrixGraph_matrix_list_factory_true() {
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph graphList = matrixGraphFactory.createMatrixGraph(verticesList, list1, false);
		
		assertEquals(graphMatrix, graphList);
	}
	
	@Test
	public void listGraph_edges_matrix_factory_true() {
		ListGraph graphEdges = listGraphFactory.createListGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		ListGraph graphMatrix = listGraphFactory.createListGraph(verticesList, matrix1, false);
	
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void listGraph_edges_list_factory_true() {
		ListGraph graphEdges = listGraphFactory.createListGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, false);
	
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void listGraph_matrix_list_factory_true() {
		ListGraph graphMatrix = listGraphFactory.createListGraph(verticesList, matrix1, false);
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, false);
	
		assertEquals(graphMatrix, graphList);
	}	
	
	// equals - same graph no matter if EdgesGraph, MatrixGraph, ListGraph
	// undirected
	@Test
	public void abstractGraph_edges_matrix_equals_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
	
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void abstractGraph_edges_list_equals_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, false);
	
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void abstractGraph_matrix_list_equals_true() {
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, false);
	
		assertEquals(graphMatrix, graphList);
	}
	
	// directed
	@Test
	public void abstractGraph_edges_matrix_directed_equals_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges3);
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
	
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void abstractGraph_edges_list_directed_equals_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges3);
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, true);
	
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void abstractGraph_matrix_list_directed_equals_true() {
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list1, true);
	
		assertEquals(graphMatrix, graphList);
	}
	
	// makeUndirected, makeDirected
	// EdgesGraph 
	
	@Test
	public void edgesGraph_makeUndirected_true() {
		EdgesGraph directed = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		EdgesGraph undirected = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
	
		assertEquals(undirected, directed.makeUndirected());
	}
	
	@Test
	public void edgesGraph_makeDirected_true() {
		EdgesGraph undirected = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		EdgesGraph directed = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges2);
	
		assertEquals(directed, undirected.makeDirected());
	}
	
	// MatrixGraph
	
	@Test
	public void matrixGraph_makeUndirected_true() {
		MatrixGraph directed = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		MatrixGraph undirected = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
	
		assertEquals(undirected, directed.makeUndirected());
	}
	
	@Test
	public void matrixGraph_makeDirected_true() {
		MatrixGraph undirected = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph directed = matrixGraphFactory.createMatrixGraph(verticesList, matrix2, true);
	
		assertEquals(directed, undirected.makeDirected());
	}
	
	// ListGraph
	
	@Test
	public void listGraph_makeUndirected_true() {
		ListGraph directed = listGraphFactory.createListGraph(verticesList, list1, true);
		ListGraph undirected = listGraphFactory.createListGraph(verticesList, list1, false);
	
		assertEquals(undirected, directed.makeUndirected());
	}
	
	@Test
	public void listGraph_makeDirected_true() {
		ListGraph undirected = listGraphFactory.createListGraph(verticesList, list1, false);
		ListGraph directed = listGraphFactory.createListGraph(verticesList, list2, true);
	
		assertEquals(directed.getDirectedEdges().size(), undirected.makeDirected().getDirectedEdges().size());
	}
	
	// getEdgesAt/From/To; getDegree/Out/In
	// EdgesGraph
	@Test
	public void edgesGraph_edgesAt() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		Multiset<Edge> edgesAt1 = HashMultiset.create();
		edgesAt1.add(e1u);
		edgesAt1.add(e1u);
		edgesAt1.add(e3u);
		edgesAt1.add(undirectedLoop);
		
		assertEquals(edgesAt1, graph.getEdgesAt(v1));
	}
	
	@Test
	public void edgesGraph_degreeAt() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		
		assertEquals(4, graph.getDegreeAt(v1));
	}
	
	@Test
	public void edgesGraph_edgesFrom() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		Multiset<Edge> edgesFrom1 = HashMultiset.create();
		edgesFrom1.add(e1d);
		edgesFrom1.add(e1d);
		edgesFrom1.add(directedLoop);
		
		assertEquals(edgesFrom1, graph.getEdgesFrom(v1));
	}
	
	@Test
	public void edgesGraph_outdegreeAt() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		
		assertEquals(3, graph.getOutdegreeAt(v1));
	}
	
	@Test
	public void edgesGraph_edgesTo() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		Multiset<Edge> edgesTo1 = HashMultiset.create();
		edgesTo1.add(e3d);
		edgesTo1.add(directedLoop);
		
		assertEquals(edgesTo1, graph.getEdgesTo(v1));
	}
	
	@Test
	public void edgesGraph_indegreeAt() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		
		assertEquals(2, graph.getIndegreeAt(v1));
	}
	
	// MatrixGraph
	@Test
	public void matrixGraph_edgesAt() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		Multiset<Edge> edgesAt1 = HashMultiset.create();
		edgesAt1.add(e1u);
		edgesAt1.add(e1u);
		edgesAt1.add(e3u);
		edgesAt1.add(undirectedLoop);
		
		assertEquals(edgesAt1, graph.getEdgesAt(v1));		
	}

	@Test
	public void matrixGraph_degreeAt() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		
		assertEquals(4, graph.getDegreeAt(v1));		
	}

	@Test
	public void matrixGraph_edgesFrom() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		Multiset<Edge> edgesFrom1 = HashMultiset.create();
		edgesFrom1.add(e1d);
		edgesFrom1.add(e1d);
		edgesFrom1.add(e3dOpposite);
		edgesFrom1.add(directedLoop);
		
		assertEquals(edgesFrom1, graph.getEdgesFrom(v1));
	}
	
	@Test
	public void matrixGraph_outdegreeAt() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		
		assertEquals(4, graph.getOutdegreeAt(v1));
	}
	
	@Test
	public void matrixGraph_edgesTo() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		Multiset<Edge> edgesTo1 = HashMultiset.create();
		edgesTo1.add(e1dOpposite);
		edgesTo1.add(e1dOpposite);
		edgesTo1.add(e3d);
		edgesTo1.add(directedLoop);
		
		assertEquals(edgesTo1, graph.getEdgesTo(v1));
	}
	
	@Test
	public void matrixGraph_indegreeAt() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		
		assertEquals(4, graph.getIndegreeAt(v1));
	}
	
	// ListGraph
	@Test
	public void listGraph_edgesAt() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, false);
		Multiset<Edge> edgesAt1 = HashMultiset.create();
		edgesAt1.add(e1u);
		edgesAt1.add(e1u);
		edgesAt1.add(e3u);
		edgesAt1.add(undirectedLoop);
		
		assertEquals(edgesAt1, graph.getEdgesAt(v1));		
	}
	
	@Test
	public void listGraph_degreeAt() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, false);
		
		assertEquals(4, graph.getDegreeAt(v1));		
	}
	
	@Test
	public void listGraph_edgesFrom() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, true);
		Multiset<Edge> edgesFrom1 = HashMultiset.create();
		edgesFrom1.add(e1d);
		edgesFrom1.add(e1d);
		edgesFrom1.add(e3dOpposite);
		edgesFrom1.add(directedLoop);
		
		assertEquals(edgesFrom1, graph.getEdgesFrom(v1));
	}
	
	@Test
	public void listGraph_outdegreeAt() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, true);
		
		assertEquals(4, graph.getOutdegreeAt(v1));
	}
	
	@Test
	public void listGraph_edgesTo() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, true);
		Multiset<Edge> edgesTo1 = HashMultiset.create();
		edgesTo1.add(e1dOpposite);
		edgesTo1.add(e1dOpposite);
		edgesTo1.add(e3d);
		edgesTo1.add(directedLoop);
		
		assertEquals(edgesTo1, graph.getEdgesTo(v1));
	}
	
	@Test
	public void listGraph_indegreeAt() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, true);
		
		assertEquals(4, graph.getIndegreeAt(v1));
	}
	
	// add/removeVertex
	
	// add/removeEdge
	
	// isEulerian
	
	// isPerfectMatching (edgesgraph, matrixgraph)
	
	// getCycle, mergeTours, getEulerianCycle (edgesgraph)
}
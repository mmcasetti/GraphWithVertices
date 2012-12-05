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
	
	public int[][] matrix1 = {{ 1, 2, 1 },
							  { 2, 0, 1 },
							  { 1, 1, 0 }};
	public int[][] matrix2 = {{ 2, 4, 2 },
			 				  { 4, 0, 2 },
			 				  { 2, 2, 0 }};
	
	public List<Multiset<Vertex>> list = Lists.newArrayList();
	@Before
	public void initializeList() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		list.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		list.add(multiset3);
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
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, list, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void edgesGraph_list_directed_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesList, list, true);
		
		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_list_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void matrixGraph_list_directed_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list, true);
		
		assertEquals(graph, graph);
	}

	@Test
	public void listGraph_list_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list, false);
		
		assertEquals(graph, graph);
	}

	@Test
	public void listGraph_list_directed_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list, true);
		
		assertEquals(graph, graph);
	}

	// Factories, equals - same graph no matter if given via edges & vertices, matrix, list
	
	@Test
	public void edgesGraph_edges_matrix_factory_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		EdgesGraph graphMatrix = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);
		
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void edgesGraph_edges_list_factory_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges, HashMultiset.<Edge>create());
		EdgesGraph graphList = edgesGraphFactory.createEdgesGraph(verticesList, list, false);
		
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void edgesGraph_matrix_list_factory_true() {
		EdgesGraph graphMatrix = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);
		EdgesGraph graphList = edgesGraphFactory.createEdgesGraph(verticesList, list, false);
		
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
		MatrixGraph graphList = matrixGraphFactory.createMatrixGraph(verticesList, list, false);
		
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void matrixGraph_matrix_list_factory_true() {
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph graphList = matrixGraphFactory.createMatrixGraph(verticesList, list, false);
		
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
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list, false);
	
		assertEquals(graphEdges, graphList);
	}
	
	@Test
	public void listGraph_matrix_list_factory_true() {
		ListGraph graphMatrix = listGraphFactory.createListGraph(verticesList, matrix1, false);
		ListGraph graphList = listGraphFactory.createListGraph(verticesList, list, false);
	
		assertEquals(graphMatrix, graphList);
	}
	
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
	
		assertEquals(undirected.makeDirected(), directed);
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
	
		assertEquals(undirected.makeDirected(), directed);
	}
	
	// ListGraph
	
//	// EdgesGraph
//	public EdgesGraphFactory edgesFactory = new EdgesGraphFactory();
//
//	Set<Vertex> vertices = new HashSet<Vertex>();
//	Vertex v1 = new Vertex();
//	Vertex v2 = new Vertex();
//	Vertex v3 = new Vertex();	
//	Multiset<Edge> edges = HashMultiset.create();		
//	Edge e0 = Edge.between(v1).and(v2);
//	Edge e1 = Edge.between(v2).and(v3);
//	Edge e2 = Edge.between(v3).and(v1);
//	
//	@Before
//	public void graph() {	
//		vertices.add(v1);
//		vertices.add(v2);
//		vertices.add(v3);
//		
//		edges.add(e0);
//		edges.add(e1);
//		edges.add(e2);
//	}
//	
//	Set<Vertex> lessVertices = new HashSet<Vertex>();	
//	Vertex v4 = new Vertex();
//	Vertex v5 = new Vertex();
//	Vertex v6 = new Vertex();
//	Set<Vertex> newVertices = new HashSet<Vertex>();
//	@Before
//	public void simpleEdgeGraph_addVertices() {
//		lessVertices.add(v1);
//		lessVertices.add(v2);
//		lessVertices.add(v3);
//		
//		vertices.add(v4);
//		vertices.add(v5);
//		vertices.add(v6);
//		
//		newVertices.add(v4);
//		newVertices.add(v5);
//		newVertices.add(v6);
//	}
//
//	Multiset<Edge> lessEdges = HashMultiset.create();	
//	Edge e4 = Edge.between(v4).and(v3);
//	Edge e5 = Edge.between(v4).and(v5);
//	Edge e6 = Edge.between(v6).and(v5);
//	@Before
//	public void simpleEdgeGraph_addEdges() {
//		lessEdges.add(e0);
//		lessEdges.add(e1);
//		lessEdges.add(e2);
//		
//		edges.add(e4);
//		edges.add(e5);
//		edges.add(e6);
//	}
//	
//	Set<Vertex> isolated = new HashSet<Vertex>();
//	Multiset<Edge> empty = HashMultiset.create();
//	@Before
//	public void isolatedVertices() {
//		isolated.add(v1);
//		isolated.add(v2);
//		isolated.add(v3);
//	}
//	
//	Set<Vertex> largeGraphVertices = new HashSet<Vertex>();
//	Vertex v07 = new Vertex();
//	Vertex v08 = new Vertex();
//	Vertex v09 = new Vertex();
//	Vertex v10 = new Vertex();
//	Vertex v11 = new Vertex();
//	Vertex v12 = new Vertex();
//	Vertex v13 = new Vertex();
//	Vertex v14 = new Vertex();	
//	Multiset<Edge> largeGraphEdges = HashMultiset.create();
//	Edge e07 = Edge.between(v07).and(v08);
//	Edge e08 = Edge.between(v07).and(v08);
//	Edge e09 = Edge.between(v08).and(v09);
//	Edge e10 = Edge.between(v09).and(v10);
//	Edge e11 = Edge.between(v10).and(v11);
//	Edge e12 = Edge.between(v11).and(v12);
//	Edge e13 = Edge.between(v12).and(v13);
//	Edge e14 = Edge.between(v13).and(v14);
//	Edge e15 = Edge.between(v09).and(v11);
//	Edge e16 = Edge.between(v09).and(v13);
//	Edge e17 = Edge.between(v11).and(v13);
//	Edge e18 = Edge.between(v14).and(v08);
//	@Before
//	public void largeGraph() {
//		largeGraphVertices.add(v07);
//		largeGraphVertices.add(v08);
//		largeGraphVertices.add(v09);
//		largeGraphVertices.add(v10);
//		largeGraphVertices.add(v11);
//		largeGraphVertices.add(v12);		
//		largeGraphVertices.add(v13);
//		largeGraphVertices.add(v14);
//
//		largeGraphEdges.add(e07);
//		largeGraphEdges.add(e08);
//		largeGraphEdges.add(e09);
//		largeGraphEdges.add(e10);
//		largeGraphEdges.add(e11);
//		largeGraphEdges.add(e12);
//		largeGraphEdges.add(e13);
//		largeGraphEdges.add(e14);
//		largeGraphEdges.add(e15);
//		largeGraphEdges.add(e16);
//		largeGraphEdges.add(e17);
//		largeGraphEdges.add(e18);
//	}
//	Multiset<Edge> matching = HashMultiset.create();
//	@Before
//	public void matching() {
//		matching.add(e07);
//		matching.add(e10);
//		matching.add(e12);
//		matching.add(e14);
//	}
//	Multiset<Edge> notMatching = HashMultiset.create();
//	@Before
//	public void notMatching() {
//		notMatching.addAll(matching);
//		notMatching.add(e08);
//	}
//	
//	
//	
//	// equals	
//	AbstractGraph graph = edgesFactory.createEdgesGraph(vertices, edges);	
//	AbstractGraph otherGraph = edgesFactory.createEdgesGraph(vertices, edges);		
//	@Test
//	public void equals_Edges_true() {		
//		assertEquals(graph, otherGraph);
//	}
//
//	// add vertices
//	AbstractGraph lessVerticesGraph = edgesFactory.createEdgesGraph(lessVertices, edges);	
//	@Test
//	public void addVertices_true() {
//		lessVerticesGraph.addVertices(newVertices);
//		assertEquals(graph, lessVerticesGraph);
//	}
//	
//	// add edge (both methods)
//	AbstractGraph lessEdgesGraph = edgesFactory.createEdgesGraph(vertices, lessEdges);	
//	@Test
//	public void addEdges_true() {
//		lessEdgesGraph.addUndirectedEdge(e4);
//		lessEdgesGraph.addUndirectedEdge(v4, v5);
//		lessEdgesGraph.addUndirectedEdge(e6);
//		assertEquals(graph, lessEdgesGraph);
//	}
//	
//	// remove vertex
//	AbstractGraph cycle = edgesFactory.createEdgesGraph(lessVertices, lessEdges);
//	@Test
//	public void removeVertex_true() {
//		graph.removeVertex(v6);
//		graph.removeVertex(v5);
//		graph.removeVertex(v4);
//		assertEquals(cycle, graph);
//	}
//
//	// remove edge
//	AbstractGraph isolatedVertices = edgesFactory.createEdgesGraph(isolated, empty);
//	@Test
//	public void removeEdge_edges_true() {
//		cycle.removeEdge(e0);
//		cycle.removeEdge(e1);
//		cycle.removeEdge(e2);
//		assertEquals(cycle, isolatedVertices);
//	}
//	
//	@Test
//	public void removeEdge_vertices_true() {
//		cycle.removeEdge(v1, v2);
//		cycle.removeEdge(v2, v3);
//		cycle.removeEdge(v3, v1);
//		assertEquals(cycle, isolatedVertices);
//	}
//	
//	@Test
//	public void removeEdge_vertices_true_2() {
//		cycle.removeEdge(v2, v1);
//		assertEquals(2, cycle.getUndirectedEdges().size());
//	}
//
//	@Test
//	public void removeEdge_vertices_doubleEdge() {
//		cycle.addUndirectedEdge(v1, v2);
//		cycle.removeEdge(v1, v2);		
//		assertEquals(3, cycle.getUndirectedEdges().size());
//	}
//
//	@Test
//	public void removeEdge_vertices_doubleEdge_reverse() {
//		cycle.addUndirectedEdge(v1, v2);
//		cycle.removeEdge(v2, v1);		
//		assertEquals(3, cycle.getUndirectedEdges().size());
//	}
//
//	// isEulerian
////	@Test
////	public void isEulerian_true() {
////		assertTrue(cycle.isEulerian());
////	}
////	@Test
////	public void isEulerian_isolated_true() {
////		assertTrue(isolatedVertices.isEulerian());
////	}
//	
//	// isPerfectMatching
//	EdgesGraph largeGraph = edgesFactory.createEdgesGraph(largeGraphVertices, largeGraphEdges);
//	@Test
//	public void isPerfectMatching_true() {
//		assertTrue(largeGraph.isPerfectMatching(matching));
//	}
//	@Test
//	public void isPerfectMatching_false() {
//		assertFalse(largeGraph.isPerfectMatching(notMatching));
//	}	
//	
//	// getCycle
//	@Test
//	public void getCycle_existence_simple() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		assertTrue(cycle3.getCycle(cycle3, v1).isPresent());		
//	}
//
//	@Test
//	public void getCycle_length_simple() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		assertEquals(3, cycle3.getCycle(cycle3, v1).get().size());		
//	}	
//	
//	@Test
//	public void getCycle_existence() {
//		assertTrue(largeGraph.getCycle(largeGraph, v07).isPresent());
//	}
//
//	@Test
//	public void getCycle_length() {
//		assertTrue(largeGraph.getCycle(largeGraph, v07).get().size() >=2);
//	}
//		
//	// getEulerianCycle
//	@Test
//	public void getEulerianCycle_existence_simple() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		assertFalse(cycle3.getEulerianCycle(cycle3, v1).isEmpty());		
//	}
//
//	@Test
//	public void getEulerianCycle_length_simple() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		assertEquals(3, cycle3.getEulerianCycle(cycle3, v1).size());		
//	}	
//	
//	@Test
//	public void getEulerianCycle_existence() {
//		assertFalse(largeGraph.getEulerianCycle(largeGraph, v07).isEmpty());
//	}
//
//	@Test
//	public void getEulerianCycle_length() {
//		for (int i = 0; i < 100; i++) {
//			assertEquals(12, largeGraph.getEulerianCycle(largeGraph, v07).size());			
//		}
//	}
//	// a couple of times returned 11 instead of 12! WHY? TODO check
//	
//	@Test
//	public void copy_preliminary_test() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		EdgesGraph cycle = cycle3.copy();
//	
//		cycle.removeEdge(v1, v2);
//		assertEquals(2, cycle.getUndirectedEdges().size());
//	}
//	
//	@Test
//	public void copy_original_is_not_the_same() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		EdgesGraph cycle = cycle3.copy();
//	
//		cycle.removeEdge(v1, v2);
//		assertEquals(3, cycle3.getUndirectedEdges().size());
//	}
//	
//	@Test
//	public void copy() {
//		EdgesGraph cycle3 = new EdgesGraph(isolated, empty);
//		cycle3.addUndirectedEdge(e0);
//		cycle3.addUndirectedEdge(e1);
//		cycle3.addUndirectedEdge(e2);
//		
//		EdgesGraph cycle = cycle3.copy();
//	
//		cycle.removeEdge(v1, v2);
//		cycle.removeEdge(v2, v3);
//		Multiset<Edge> edges = HashMultiset.create();
//		edges.add(Edge.between(v3).and(v1));
//		assertEquals(edges, cycle.getUndirectedEdges());	
//	}
//	
//	// MatrixGraph
//	public MatrixGraphFactory matrixFactory = new MatrixGraphFactory();
//
//	@Test
//	public void equals_matrix_true() {
//		
//		int[][] adjacency = {{ 0, 1, 0 },
//							 { 1, 0, 1 },
//							 { 0, 1, 1 }};
//
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		AbstractGraph graph = matrixFactory.createMatrixGraph(adjacency, vertices);
//		AbstractGraph otherGraph = matrixFactory.createMatrixGraph(adjacency, vertices);
//		
//		assertEquals(graph, otherGraph);
//	}
//	
//	// overriding equals works!
//	@Test
//	public void equals_matrix_withoutVertices_true() {
//		
//		int[][] adjacency = {{ 0, 1, 0 },
//							 { 1, 0, 1 },
//							 { 0, 1, 1 }};
//
//		AbstractGraph graph = matrixFactory.createMatrixGraph(adjacency);
//		AbstractGraph otherGraph = matrixFactory.createMatrixGraph(adjacency);
//		
//		assertEquals(graph, otherGraph);
//	}
//	
//	// factory with edges creates the same edgesGraph and MatrixGraph
//	@Test
//	public void factory_edgesGraph_matrixGraph_edges() {
//		AbstractGraph graph = edgesFactory.createEdgesGraph(vertices, edges);
//		AbstractGraph otherGraph = matrixFactory.createMatrixGraph(vertices, edges);
//		
//		// fails! Some vertices are not the same!
//		assertEquals(graph.getUndirectedEdges(), otherGraph.getUndirectedEdges());
//	}
//	
//	// factory with matrix creates the same edgesGraph and MatrixGraph
//	@Test
//	public void factory_edgesGraph_matrixGraph_matrix() {
//		int[][] adjacency = {{ 0, 1, 0 },
//							 { 1, 0, 1 },
//							 { 0, 1, 1 }};
//
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		AbstractGraph graph = edgesFactory.createEdgesGraph(adjacency, vertices);
//		AbstractGraph otherGraph = matrixFactory.createMatrixGraph(adjacency, vertices);
//		
//		assertEquals(graph, otherGraph);
//	}
//
//	@Test
//	public void addVertices_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//							 { 1, 2, 1 },
//							 { 0, 1, 0 }};
//
//		MatrixGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
//
//		Vertex v1 = new Vertex();
//		Vertex v2 = new Vertex();
//		Set<Vertex> newVertices = Sets.newHashSet(v1, v2);
//		
//		graphIncidence.addVertices(newVertices);
//		
//		int[][] newIncidence = {{ 0, 1, 0, 0, 0 },
//				 				{ 1, 2, 1, 0, 0 },
//				 				{ 0, 1, 0, 0, 0 },
//				 				{ 0, 0, 0, 0, 0 },
//				 				{ 0, 0, 0, 0, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
//		
//		assertEquals(graphIncidence, newGraph);
//	}
//	
//	@Test
//	public void removeVertex_matrix() {
//		int[][] incidence =  {{ 0, 1, 0, 0, 0 },
//							  { 1, 2, 1, 0, 0 },
//							  { 0, 1, 0, 0, 0 },
//							  { 0, 0, 0, 0, 0 },
//							  { 0, 0, 0, 0, 0 }};
//		
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		Vertex v4 = new Vertex();
//		vertices.add(v4);
//		Vertex v5 = new Vertex();
//		vertices.add(v5);
//
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//		
//		graph.removeVertex(v3);
//
//		int[][] newIncidence =  {{ 0, 1, 0, 0 },
//								 { 1, 2, 0, 0 },
//								 { 0, 0, 0, 0 },
//								 { 0, 0, 0, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
//		
//		assertEquals(graph, newGraph);
//	}
//	
//	@Test
//	public void addEdge_edge_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//							 { 1, 2, 1 },
//							 { 0, 1, 0 }};
//
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//		
//		Edge edge = Edge.between(v1).and(v3);
//		
//		graph.addUndirectedEdge(edge);
//		
//		int[][] newIncidence = {{ 0, 1, 1 },
//								{ 1, 2, 1 },
//								{ 1, 1, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence, vertices);
//		
//		assertEquals(graph, newGraph);
//	}
//	
//	@Test
//	public void addEdge_vertices_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//							 { 1, 2, 1 },
//							 { 0, 1, 0 }};
//		
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//				
//		graph.addUndirectedEdge(v1, v3);
//		
//		int[][] newIncidence = {{ 0, 1, 1 },
//								{ 1, 2, 1 },
//								{ 1, 1, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence, vertices);
//		
//		assertEquals(graph, newGraph);
//	}
//
//	@Test
//	public void addEdge_loop_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//							 { 1, 2, 1 },
//							 { 0, 1, 0 }};
//		
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//				
//		graph.addUndirectedEdge(v1, v1);
//		
//		int[][] newIncidence = {{ 1, 1, 0 },
//								{ 1, 2, 1 },
//								{ 0, 1, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence, vertices);
//		
//		assertEquals(graph, newGraph);
//	}
//
//	@Test
//	public void removeEdge_edge_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//						 	 { 1, 2, 1 },
//						 	 { 0, 1, 0 }};
//		
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//		
//		Edge edge = Edge.between(v1).and(v2);
//		
//		graph.removeEdge(edge);
//		
//		int[][] newIncidence = {{ 0, 0, 0 },
//								{ 0, 2, 1 },
//								{ 0, 1, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence, vertices);
//		
//		assertEquals(graph, newGraph);		
//	}
//	
//	@Test
//	public void removeEdge_vertices_matrix() {
//		int[][] incidence = {{ 0, 1, 0 },
//							 { 1, 2, 1 },
//							 { 0, 1, 0 }};
//		
//		List<Vertex> vertices = Lists.newArrayList();
//		Vertex v1 = new Vertex();
//		vertices.add(v1);
//		Vertex v2 = new Vertex();
//		vertices.add(v2);
//		Vertex v3 = new Vertex();
//		vertices.add(v3);
//		
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence, vertices);
//				
//		graph.removeEdge(v1, v2);
//		
//		int[][] newIncidence = {{ 0, 0, 0 },
//								{ 0, 2, 1 },
//								{ 0, 1, 0 }};
//		
//		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence, vertices);
//		
//		assertEquals(graph, newGraph);		
//	}
//
//	@Test
//	public void isEulerian_matrix() {
//		int[][] incidence = {{ 0, 1, 1, 0 },
//							 { 1, 1, 1, 0 },
//							 { 1, 1, 0, 2 },
//							 { 0, 0, 2, 0 }};
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
//		
//		assertTrue(graph.isEulerian());
//	}
//	
//	@Test
//	public void isEulerian_matrix_false() {
//		int[][] incidence = {{ 0, 1, 1, 0 },
//							 { 1, 1, 0, 0 },
//							 { 1, 0, 0, 2 },
//							 { 0, 0, 2, 0 }};
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
//
//		assertFalse(graph.isEulerian());
//	}
//	
//	@Test
//	public void isPerfectMatching_matrix() {
//		int[][] incidence = {{ 0, 1, 1, 0 },
//							 { 1, 1, 1, 0 },
//							 { 1, 1, 0, 2 },
//							 { 0, 0, 2, 0 }};
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
//
//		int[][] subset = {{ 0, 1, 0, 0 },
//						  { 1, 0, 0, 0 },
//						  { 0, 0, 0, 1 },
//						  { 0, 0, 1, 0 }};
//		
//		assertTrue(graph.isPerfectMatching(subset));
//	}
//	
//	@Test
//	public void isPerfectMatching_matrix_falseWithLoops() {
//		int[][] incidence = {{ 1, 1, 1, 0 },
//							 { 1, 1, 1, 0 },
//							 { 1, 1, 0, 2 },
//							 { 0, 0, 2, 0 }};
//		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
//
//		int[][] subset = {{ 1, 0, 0, 0 },
//						  { 0, 1, 0, 0 },
//						  { 0, 0, 0, 1 },
//						  { 0, 0, 1, 0 }};
//		
//		assertFalse(graph.isPerfectMatching(subset));
//	}
//
//	// ListGraph
//	public ListGraphFactory listFactory = new ListGraphFactory();
//
//	public List<Vertex> verticesOfListGraph = Lists.newArrayList();
//	public ArrayList<Multiset<Vertex>> adjacencyList = Lists.newArrayList();
//	@Before
//	public void cycleWithLoop_list() {
//		for (int i = 0; i < 3; i++) {
//			Vertex v = new Vertex();
//			verticesOfListGraph.add(v);
//			Multiset<Vertex> multiset = HashMultiset.create();
//			adjacencyList.add(multiset);
//		}
//		adjacencyList.get(0).add(verticesOfListGraph.get(0));		
//		adjacencyList.get(0).add(verticesOfListGraph.get(0));		
//		adjacencyList.get(0).add(verticesOfListGraph.get(1));
//		adjacencyList.get(0).add(verticesOfListGraph.get(2));		
//		adjacencyList.get(1).add(verticesOfListGraph.get(0));
//		adjacencyList.get(1).add(verticesOfListGraph.get(2));
//		adjacencyList.get(2).add(verticesOfListGraph.get(1));
//		adjacencyList.get(2).add(verticesOfListGraph.get(0));		
//	}
//	public List<Vertex> otherVerticesOfListGraph = Lists.newArrayList();
//	public ArrayList<Multiset<Vertex>> otherAdjacencyList = Lists.newArrayList();
//	@Before
//	public void otherGraph_list() {
//		for (int i = 0; i < 3; i++) {
//			Vertex v = new Vertex();
//			otherVerticesOfListGraph.add(v);
//			Multiset<Vertex> multiset = HashMultiset.create();
//			otherAdjacencyList.add(multiset);
//		}
//		otherAdjacencyList.get(0).add(otherVerticesOfListGraph.get(0));		
//		otherAdjacencyList.get(0).add(otherVerticesOfListGraph.get(0));		
//		otherAdjacencyList.get(0).add(otherVerticesOfListGraph.get(1));
//		otherAdjacencyList.get(0).add(otherVerticesOfListGraph.get(2));		
//		otherAdjacencyList.get(1).add(otherVerticesOfListGraph.get(0));
//		otherAdjacencyList.get(1).add(otherVerticesOfListGraph.get(2));
//		otherAdjacencyList.get(2).add(otherVerticesOfListGraph.get(1));
//		otherAdjacencyList.get(2).add(otherVerticesOfListGraph.get(0));		
//	}
//	
//	@Test
//	public void equals_List_true() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		ListGraph otherGraph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		
//		assertEquals(graph, otherGraph);
//	}
//	
//	@Test
//	public void equals_equalList_List_true() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		ListGraph otherGraph = listFactory.createListGraph(otherVerticesOfListGraph, otherAdjacencyList);
//		
//		assertEquals(graph, otherGraph);
//	}
//
//	@Test
//	public void factory_List_Matrix_true() {
//		AbstractGraph listGraph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		AbstractGraph matrixGraph = matrixFactory.createMatrixGraph(verticesOfListGraph, adjacencyList);
//		
//		assertEquals(listGraph.getUndirectedEdges(), matrixGraph.getUndirectedEdges());
//	}
//
//	@Test
//	public void factory_List_Edge_true() {
//		AbstractGraph listGraph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		AbstractGraph edgesGraph = edgesFactory.createEdgesGraph(verticesOfListGraph, adjacencyList);
//		
//		assertEquals(listGraph, edgesGraph);
//	}
//	
//	@Test
//	public void addVertices_list() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		
//		Set<Vertex> newVertices = Sets.newHashSet();
//		for (int i = 0; i < 2; i++) {
//			Vertex v = new Vertex();
//			newVertices.add(v);
//		}
//		graph.addVertices(newVertices);
//		
//		otherVerticesOfListGraph.addAll(newVertices);
//		for (int i = 0; i < 2; i++) {
//			Multiset<Vertex> multiset = HashMultiset.create();
//			otherAdjacencyList.add(multiset);			
//		}	
//		ListGraph otherGraph = listFactory.createListGraph(otherVerticesOfListGraph, otherAdjacencyList);
//		
//		assertEquals(graph, otherGraph);
//	}
//
//	@Test
//	public void removeVertex_list() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		graph.removeVertex(graph.getListOfVertices().get(2));
//
//		List<Vertex> newVertices = Lists.newArrayList();
//		Vertex v0 = new Vertex();
//		newVertices.add(v0);
//		Vertex v1 = new Vertex();
//		newVertices.add(v1);
//		
//		ArrayList<Multiset<Vertex>> newList = Lists.newArrayList();
//		Multiset<Vertex> multiset0 = HashMultiset.create();
//		multiset0.add(v0);
//		multiset0.add(v0);
//		multiset0.add(v1);
//		newList.add(multiset0);
//		Multiset<Vertex> multiset1 = HashMultiset.create();
//		multiset1.add(v0);
//		newList.add(multiset1);
//
//		ListGraph newGraph = listFactory.createListGraph(newVertices, newList);
//		
//		assertEquals(graph, newGraph);	
//	}
//	
//	@Test
//	public void addEdge_edge_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//
//		Edge e = Edge.between(verticesOfListGraph.get(0)).and(verticesOfListGraph.get(2));
//		graph.addUndirectedEdge(e);
//		
//		List<Vertex> newVertices = Lists.newArrayList();
//		Vertex v0 = new Vertex();
//		newVertices.add(v0);
//		Vertex v1 = new Vertex();
//		newVertices.add(v1);
//		Vertex v2 = new Vertex();
//		newVertices.add(v2);
//		
//		ArrayList<Multiset<Vertex>> newList = Lists.newArrayList();
//		Multiset<Vertex> multiset0 = HashMultiset.create();
//		multiset0.add(v0);
//		multiset0.add(v0);
//		multiset0.add(v1);
//		multiset0.add(v2);
//		multiset0.add(v2);
//		newList.add(multiset0);
//		Multiset<Vertex> multiset1 = HashMultiset.create();
//		multiset1.add(v0);
//		multiset1.add(v2);
//		newList.add(multiset1);
//		Multiset<Vertex> multiset2 = HashMultiset.create();
//		multiset2.add(v0);
//		multiset2.add(v0);
//		multiset2.add(v1);
//		newList.add(multiset2);
//		
//		ListGraph newGraph = listFactory.createListGraph(newVertices, newList);
//		
//		assertEquals(graph, newGraph);
//	}
//	
//	@Test
//	public void addEdge_vertices_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//
//		graph.addUndirectedEdge(verticesOfListGraph.get(0), verticesOfListGraph.get(2));
//		
//		List<Vertex> newVertices = Lists.newArrayList();
//		Vertex v0 = new Vertex();
//		newVertices.add(v0);
//		Vertex v1 = new Vertex();
//		newVertices.add(v1);
//		Vertex v2 = new Vertex();
//		newVertices.add(v2);
//		
//		ArrayList<Multiset<Vertex>> newList = Lists.newArrayList();
//		Multiset<Vertex> multiset0 = HashMultiset.create();
//		multiset0.add(v0);
//		multiset0.add(v0);
//		multiset0.add(v1);
//		multiset0.add(v2);
//		multiset0.add(v2);
//		newList.add(multiset0);
//		Multiset<Vertex> multiset1 = HashMultiset.create();
//		multiset1.add(v0);
//		multiset1.add(v2);
//		newList.add(multiset1);
//		Multiset<Vertex> multiset2 = HashMultiset.create();
//		multiset2.add(v0);
//		multiset2.add(v0);
//		multiset2.add(v1);
//		newList.add(multiset2);
//		
//		ListGraph newGraph = listFactory.createListGraph(newVertices, newList);
//		
//		assertEquals(graph, newGraph);
//	}
//		
//	@Test
//	public void removeEdge_edge_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//
//		Edge e = Edge.between(verticesOfListGraph.get(0)).and(verticesOfListGraph.get(0));
//		graph.removeEdge(e);
//		
//		List<Vertex> newVertices = Lists.newArrayList();
//		Vertex v0 = new Vertex();
//		newVertices.add(v0);
//		Vertex v1 = new Vertex();
//		newVertices.add(v1);
//		Vertex v2 = new Vertex();
//		newVertices.add(v2);
//		
//		ArrayList<Multiset<Vertex>> newList = Lists.newArrayList();
//		Multiset<Vertex> multiset0 = HashMultiset.create();
//		multiset0.add(v0);
//		multiset0.add(v1);
//		multiset0.add(v2);
//		newList.add(multiset0);
//		Multiset<Vertex> multiset1 = HashMultiset.create();
//		multiset1.add(v0);
//		multiset1.add(v2);
//		newList.add(multiset1);
//		Multiset<Vertex> multiset2 = HashMultiset.create();
//		multiset2.add(v0);
//		multiset2.add(v1);
//		newList.add(multiset2);
//		
//		ListGraph newGraph = listFactory.createListGraph(newVertices, newList);
//		
//		assertEquals(graph, newGraph);
//	}
//	
//	@Test
//	public void removeEdge_vertices_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//
//		graph.removeEdge(verticesOfListGraph.get(0), verticesOfListGraph.get(0));
//		
//		List<Vertex> newVertices = Lists.newArrayList();
//		Vertex v0 = new Vertex();
//		newVertices.add(v0);
//		Vertex v1 = new Vertex();
//		newVertices.add(v1);
//		Vertex v2 = new Vertex();
//		newVertices.add(v2);
//		
//		ArrayList<Multiset<Vertex>> newList = Lists.newArrayList();
//		Multiset<Vertex> multiset0 = HashMultiset.create();
//		multiset0.add(v0);
//		multiset0.add(v1);
//		multiset0.add(v2);
//		newList.add(multiset0);
//		Multiset<Vertex> multiset1 = HashMultiset.create();
//		multiset1.add(v0);
//		multiset1.add(v2);
//		newList.add(multiset1);
//		Multiset<Vertex> multiset2 = HashMultiset.create();
//		multiset2.add(v0);
//		multiset2.add(v1);
//		newList.add(multiset2);
//		
//		ListGraph newGraph = listFactory.createListGraph(newVertices, newList);
//		
//		assertEquals(graph, newGraph);
//	}
//
//	@Test
//	public void isEulerian_true_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		
//		assertTrue(graph.isEulerian());
//	}
//
//	@Test
//	public void isEulerian_false_List() {
//		ListGraph graph = listFactory.createListGraph(verticesOfListGraph, adjacencyList);
//		graph.removeEdge(verticesOfListGraph.get(0), verticesOfListGraph.get(2));
//		
//		assertFalse(graph.isEulerian());
//	}
}
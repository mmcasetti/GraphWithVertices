package graphimplementations;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
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
	public Multiset<Edge> undirectedEdges1 = HashMultiset.create();
	@Before
	public void initializeUndirectedEdges() {
		undirectedEdges1.add(e1u);
		undirectedEdges1.add(e1u);
		undirectedEdges1.add(e2u);
		undirectedEdges1.add(e3u);
		undirectedEdges1.add(undirectedLoop);
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
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void edgesGraph_edges_directed_factory_true() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_edges_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void matrixGraph_edges_directed_factory_true() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_edges_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());

		assertEquals(graph, graph);
	}
	
	@Test
	public void listGraph_edges_directed_factory_true() {
		ListGraph graph = listGraphFactory.createListGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());

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
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		EdgesGraph graphMatrix = edgesGraphFactory.createEdgesGraph(verticesList, matrix1, false);
		
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void edgesGraph_edges_list_factory_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
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
		MatrixGraph graphEdges = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void matrixGraph_edges_list_factory_true() {
		MatrixGraph graphEdges = matrixGraphFactory.createMatrixGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
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
		ListGraph graphEdges = listGraphFactory.createListGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		ListGraph graphMatrix = listGraphFactory.createListGraph(verticesList, matrix1, false);
	
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void listGraph_edges_list_factory_true() {
		ListGraph graphEdges = listGraphFactory.createListGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
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
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		MatrixGraph graphMatrix = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
	
		assertEquals(graphEdges, graphMatrix);
	}
	
	@Test
	public void abstractGraph_edges_list_equals_true() {
		EdgesGraph graphEdges = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
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
		EdgesGraph undirected = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
	
		assertEquals(undirected, directed.makeUndirected());
	}
	
	@Test
	public void edgesGraph_makeDirected_true() {
		EdgesGraph undirected = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
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
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		Multiset<Edge> edgesAt1 = HashMultiset.create();
		edgesAt1.add(e1u);
		edgesAt1.add(e1u);
		edgesAt1.add(e3u);
		edgesAt1.add(undirectedLoop);
		
		assertEquals(edgesAt1, graph.getEdgesAt(v1));
	}
	
	@Test
	public void edgesGraph_degreeAt() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		
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
	
	// addVertices/removeVertex

	public Vertex v4 = new Vertex();
	public Vertex v5 = new Vertex();
	public Set<Vertex> newVerticesSet = Sets.newHashSet(v4, v5);
	public Set<Vertex> largerVerticesSet = Sets.newHashSet(v1, v2, v3, v4, v5);
	
	public List<Vertex> largerVerticesList = Lists.newArrayList(v1, v2, v3, v4, v5);
	
	public int[][] matrix3 = {{ 1, 2, 1, 0, 0 },
							  { 2, 0, 1, 0, 0 },
							  { 1, 1, 0, 0, 0 },
							  { 0, 0, 0, 0, 0 },
							  { 0, 0, 0, 0, 0 }};
	
	public List<Multiset<Vertex>> list3 = Lists.newArrayList();
	@Before
	public void initializeList3() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list3.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		list3.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		list3.add(multiset3);
		Multiset<Vertex> multiset4 = HashMultiset.create();
		list3.add(multiset4);
		Multiset<Vertex> multiset5 = HashMultiset.create();
		list3.add(multiset5);
	}

	// EdgesGraph
	
	@Test
	public void edgesGraph_addVertices() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		
		graph.addVertices(newVerticesSet);
		
		assertEquals(largerGraph, graph);
	}
	
	@Test
	public void edgesGraph_removeVertex() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, undirectedEdges1, HashMultiset.<Edge>create());

		largerGraph.removeVertex(v4);
		largerGraph.removeVertex(v5);
		
		assertEquals(graph, largerGraph);
	}
	
	// MatrixGraph	
	@Test
	public void matrixGraph_addVertices() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(largerVerticesList, matrix3, false);
		
		graph.addVertices(newVerticesSet);
		
		assertEquals(largerGraph, graph);		
	}
	
	@Test
	public void matrixGraph_removeVertex() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(largerVerticesList, matrix3, false);
		
		largerGraph.removeVertex(v4);
		largerGraph.removeVertex(v5);
		
		assertEquals(graph, largerGraph);
	}
	
	// ListGraph
	@Test
	public void listGraph_addVertices() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, false);
		ListGraph largerGraph = listGraphFactory.createListGraph(largerVerticesList, list3, false);
		
		graph.addVertices(newVerticesSet);
		
		assertEquals(largerGraph, graph);		
	}
	
	@Test
	public void listGraph_removeVertex() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list1, false);
		ListGraph largerGraph = listGraphFactory.createListGraph(largerVerticesList, list3, false);
		
		largerGraph.removeVertex(v4);
		largerGraph.removeVertex(v5);
		
		assertEquals(graph, largerGraph);
	}
	
	// add/removeEdge
	
	public Edge e4u = Edge.between(v3).and(v2);
	public Multiset<Edge> largerUndirectedEdges = HashMultiset.create();
	@Before
	public void initializeLargerUndirectedEdges() {
		largerUndirectedEdges.add(e1u);
		largerUndirectedEdges.add(e1u);
		largerUndirectedEdges.add(e2u);
		largerUndirectedEdges.add(e3u);
		largerUndirectedEdges.add(e4u);
		largerUndirectedEdges.add(undirectedLoop);
	}
	
	public Edge e4d = Edge.from(v3).to(v2);
	public Multiset<Edge> largerDirectedEdges = HashMultiset.create();
	@Before
	public void initializeLargerdirectedEdges() {
		largerDirectedEdges.add(e1d);
		largerDirectedEdges.add(e1d);
		largerDirectedEdges.add(e2d);
		largerDirectedEdges.add(e3d);
		largerDirectedEdges.add(e4d);
		largerDirectedEdges.add(directedLoop);
	}

	public int[][] matrix4 = {{ 1, 2, 1 },
							  { 2, 0, 2 },
							  { 1, 2, 0 }};
	public int[][] matrix5 = {{ 1, 2, 1 },
							  { 2, 0, 1 },
							  { 1, 2, 0 }};
	
	public List<Multiset<Vertex>> list4 = Lists.newArrayList();
	@Before
	public void initializeList4() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list4.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		multiset2.add(v3);
		list4.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		multiset3.add(v2);
		list4.add(multiset3);
	}
	public List<Multiset<Vertex>> list5 = Lists.newArrayList();
	@Before
	public void initializeList5() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v2);
		multiset1.add(v3);
		list5.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v1);
		multiset2.add(v3);
		list5.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		multiset3.add(v2);
		list5.add(multiset3);
	}

	// EdgesGraph
	@Test
	public void edgesGraph_addEdge_edge_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(verticesSet, largerUndirectedEdges, HashMultiset.<Edge>create());
		
		graph.addUndirectedEdge(e4u);
		
		assertEquals(largerGraph, graph);
	}
	
	@Test
	public void edgesGraph_addEdge_edge_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), largerDirectedEdges);
		
		graph.addDirectedEdge(e4d);
		
		assertEquals(largerGraph, graph);
	}

	@Test
	public void edgesGraph_addEdge_vertices_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEdges1, HashMultiset.<Edge>create());
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(verticesSet, largerUndirectedEdges, HashMultiset.<Edge>create());
		
		graph.addUndirectedEdge(v2, v3);
		
		assertEquals(largerGraph, graph);
	}
	
	@Test
	public void edgesGraph_addEdge_vertices_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEdges1);
		EdgesGraph largerGraph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), largerDirectedEdges);
		
		graph.addDirectedEdge(v3, v2);
		
		assertEquals(largerGraph, graph);		
	}
	
	// MatrixGraph
	@Test
	public void matrixGraph_addEdge_edge_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, matrix4, false);
		
		graph.addUndirectedEdge(e4u);
		
		assertEquals(largerGraph, graph);
	}
	
	@Test
	public void matrixGraph_addEdge_edge_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, matrix5, true);
		
		graph.addDirectedEdge(e4d);
		
		assertEquals(largerGraph, graph);		
	}

	@Test
	public void matrixGraph_addEdge_vertices_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, matrix4, false);
		
		graph.addUndirectedEdge(v2, v3);
		
		assertEquals(largerGraph, graph);
	}
	
	@Test
	public void matrixGraph_addEdge_vertices_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix1, true);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, matrix5, true);
		
		graph.addDirectedEdge(v3, v2);
		
		assertEquals(largerGraph, graph);		
	}
	
	// ListGraph
	@Test
	public void listGraph_addEdge_edge_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, list4, false);
		
		graph.addUndirectedEdge(e4u);
		
		assertEquals(largerGraph, graph);		
	}
	
	@Test
	public void listGraph_addEdge_edge_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, true);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, list5, true);
		
		graph.addDirectedEdge(e4d);
		
		assertEquals(largerGraph, graph);		
	}

	@Test
	public void listGraph_addEdge_vertices_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, false);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, list4, false);
		
		graph.addUndirectedEdge(v2, v3);
		
		assertEquals(largerGraph, graph);		
	}
	
	@Test
	public void listGraph_addEdge_vertices_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, list1, true);
		MatrixGraph largerGraph = matrixGraphFactory.createMatrixGraph(verticesList, list5, true);
		
		graph.addDirectedEdge(v3, v2);
		
		assertEquals(largerGraph, graph);		
	}
	
	// isEulerian
	
	public Multiset<Edge> undirectedEulerianEdges = HashMultiset.create();
	@Before
	public void initializeUndirectedEulerianEdges() {
		undirectedEulerianEdges.add(e1u);
		undirectedEulerianEdges.add(e2u);
		undirectedEulerianEdges.add(e3u);
		undirectedEulerianEdges.add(undirectedLoop);
	}
	
	public Multiset<Edge> directedEulerianEdges = HashMultiset.create();
	@Before
	public void initializeDirectedEulerianEdges() {
		directedEulerianEdges.add(e1d);
		directedEulerianEdges.add(e2d);
		directedEulerianEdges.add(e3d);
		directedEulerianEdges.add(directedLoop);
	}
	
	public int[][] matrix6 = {{ 1, 1, 1 },
							  { 1, 0, 1 },
							  { 1, 1, 0 }};
	public int[][] matrix7 = {{ 1, 1, 0 },
			  				  { 0, 0, 1 },
			  				  { 1, 0, 0 }};

	public List<Multiset<Vertex>> list6 = Lists.newArrayList();
	@Before
	public void initializeList6() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		multiset1.add(v3);
		list6.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v1);
		multiset2.add(v3);
		list6.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		multiset3.add(v2);
		list6.add(multiset3);
	}	
	public List<Multiset<Vertex>> list7 = Lists.newArrayList();
	@Before
	public void initializeList7() {
		Multiset<Vertex> multiset1 = HashMultiset.create();
		multiset1.add(v1);
		multiset1.add(v2);
		list7.add(multiset1);
		Multiset<Vertex> multiset2 = HashMultiset.create();
		multiset2.add(v3);
		list7.add(multiset2);
		Multiset<Vertex> multiset3 = HashMultiset.create();
		multiset3.add(v1);
		list7.add(multiset3);
	}

	// EdgesGraph
	@Test
	public void edgesGraph_isEulerian_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, undirectedEulerianEdges, HashMultiset.<Edge>create());
		
		assertTrue(graph.isEulerian());
	}

	@Test
	public void edgesGraph_isEulerian_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSet, HashMultiset.<Edge>create(), directedEulerianEdges);
		
		assertTrue(graph.isEulerian());
	}
	
	// MatrixGraph
	@Test
	public void matrixGraph_isEulerian_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix6, false);
		
		assertTrue(graph.isEulerian());
	}

	@Test
	public void matrixGraph_isEulerian_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesList, matrix7, true);
		
		assertTrue(graph.isEulerian());
	}
	
	// ListGraph
	@Test
	public void listGraph_isEulerian_undirected() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list6, false);
		
		assertTrue(graph.isEulerian());
	}

	@Test
	public void listGraph_isEulerian_directed() {
		ListGraph graph = listGraphFactory.createListGraph(verticesList, list7, true);
		
		assertTrue(graph.isEulerian());
	}
	
	// isPerfectMatching
	public Set<Vertex> verticesSetForMatching = Sets.newHashSet(v1, v2, v3, v4);
	public List<Vertex> verticesListForMatching = Lists.newArrayList(v1, v2, v3, v4);
	
	public Edge e5u = Edge.between(v3).and(v4);
	public Multiset<Edge> undirectedEdges2 = HashMultiset.create();
	@Before
	public void initializeUndirectedEdges2() {
		undirectedEdges2.add(e1u);
		undirectedEdges2.add(e1u);
		undirectedEdges2.add(e2u);
		undirectedEdges2.add(e3u);
		undirectedEdges2.add(e5u);
		undirectedEdges2.add(undirectedLoop);
	}
	public Multiset<Edge> undirectedPerfectMatching = HashMultiset.create();
	@Before
	public void initializeUndirectedPerfectMatching() {
		undirectedPerfectMatching.add(e1u);
		undirectedPerfectMatching.add(e5u);
	}
		
	public Edge e5d = Edge.from(v3).to(v4);
	public Multiset<Edge> directedEdges4 = HashMultiset.create();
	@Before
	public void initializeDirectedEdges4() {
		directedEdges4.add(e1d);
		directedEdges4.add(e1d);
		directedEdges4.add(e2d);
		directedEdges4.add(e3d);
		directedEdges4.add(e5d);
		directedEdges4.add(directedLoop);
	}
	public Multiset<Edge> directedPerfectMatching = HashMultiset.create();
	@Before
	public void initializeDirectedPerfectMatching() {
		directedPerfectMatching.add(e1d);
		directedPerfectMatching.add(e5d);
	}
	
	public int[][] matrix8 = {{ 1, 2, 1, 0 },
							  { 2, 0, 1, 0 },
							  { 1, 1, 0, 1 },
							  { 0, 0, 1, 0 }};	
	public int[][] matrixUndirectedPerfectMatching = {{ 0, 1, 0, 0 },
							  						  { 1, 0, 0, 0 },
							  						  { 0, 0, 0, 1 },
							  						  { 0, 0, 1, 0 }};

	public int[][] matrix9 = {{ 1, 2, 1, 0 },
							  { 0, 0, 1, 0 },
							  { 1, 0, 0, 1 },
							  { 0, 0, 0, 0 }};	
	public int[][] matrixDirectedPerfectMatching = {{ 0, 1, 0, 0 },
													{ 0, 0, 0, 0 },
													{ 0, 0, 0, 1 },
													{ 0, 0, 0, 0 }};

	// EdgesGraph
	@Test
	public void edgesGraph_isPerfectMatching_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSetForMatching, undirectedEdges2, HashMultiset.<Edge>create());
		
		assertTrue(graph.isPerfectMatching(undirectedPerfectMatching));
	}
	
	@Test
	public void edgesGraph_isPerfectMatching_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(verticesSetForMatching, HashMultiset.<Edge>create(), directedEdges4);
		
		assertTrue(graph.isPerfectMatching(directedPerfectMatching));
	}
	
	// MatrixGraph
	@Test
	public void matrixGraph_isPerfectMatching_undirected() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesListForMatching, matrix8, false);
		
		assertTrue(graph.isPerfectMatching(matrixUndirectedPerfectMatching));
	}
	
	@Test
	public void matrixGraph_isPerfectMatching_directed() {
		MatrixGraph graph = matrixGraphFactory.createMatrixGraph(verticesListForMatching, matrix9, true);
		
		assertTrue(graph.isPerfectMatching(matrixDirectedPerfectMatching));
	}
	
	// getCycle, mergeTours, getEulerianCycle (EdgesGraph)
	public Edge e6u = Edge.between(v4).and(v5);
	public Edge e7u = Edge.between(v5).and(v3);
	public Multiset<Edge> undirectedEdgesForCycle = HashMultiset.create();
	@Before
	public void initializeUndirectedEdgesForCycle() {
		undirectedEdgesForCycle.add(e1u);
		undirectedEdgesForCycle.add(e2u);
		undirectedEdgesForCycle.add(e3u);
		undirectedEdgesForCycle.add(e3u);
		undirectedEdgesForCycle.add(e3u);
		undirectedEdgesForCycle.add(e5u);
		undirectedEdgesForCycle.add(e6u);
		undirectedEdgesForCycle.add(e7u);
		undirectedEdgesForCycle.add(undirectedLoop);
	}
	public Edge e6d = Edge.from(v4).to(v5);
	public Edge e7d = Edge.from(v5).to(v3);
	public Multiset<Edge> directedEdgesForCycle = HashMultiset.create();
	@Before
	public void initializeDirectedEdgesForCycle() {
		directedEdgesForCycle.add(e1d);
		directedEdgesForCycle.add(e2d);
		directedEdgesForCycle.add(e3d);
		directedEdgesForCycle.add(e3d);
		directedEdgesForCycle.add(e3dOpposite);
		directedEdgesForCycle.add(e5d);
		directedEdgesForCycle.add(e6d);
		directedEdgesForCycle.add(e7d);
		directedEdgesForCycle.add(directedLoop);
	}
	
	public List<Vertex> tour1List = Lists.newArrayList(v1, v2, v3);
	public Optional<List<Vertex>> tour1 = Optional.of(tour1List);
	public List<Vertex> tour2List = Lists.newArrayList(v5, v3, v4);
	public Optional<List<Vertex>> tour2 = Optional.of(tour2List);
	public List<Vertex> tour3List = Lists.newArrayList(v1, v2, v3, v4, v5);
	public Optional<List<Vertex>> tour3 = Optional.of(tour3List);

	@Test
	public void edgesGraph_mergeTours() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, undirectedEdgesForCycle, HashMultiset.<Edge>create());

		assertEquals(tour3, graph.mergeTours(tour1, tour2, v3));
	}

	@Test
	public void edgesGraph_getCycleInEulerian_undirected_isolated() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(Sets.newHashSet(v1), HashMultiset.<Edge>create(), HashMultiset.<Edge>create());
		
		List<Vertex> cycle = graph.getCycleInEulerian(graph, v1);
		assertEquals(cycle, Lists.newArrayList(v1));
	}
	
	public Multiset<Edge> justLoop = HashMultiset.create();
	@Before
	public void initializeJustLoop() {
		justLoop.add(undirectedLoop);
	}
	
	@Test
	public void edgesGraph_getCycleInEulerian_undirected_isolated_loop() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(Sets.newHashSet(v1), justLoop, HashMultiset.<Edge>create());
		
		List<Vertex> cycle = graph.getCycleInEulerian(graph, v1);
		assertEquals(cycle, Lists.newArrayList(v1, v1));
	}
	
	public Multiset<Edge> twoEdges = HashMultiset.create();
	@Before
	public void initializeTwoEdges() {
		twoEdges.add(e3u);
		twoEdges.add(e3u);
	}
	
	@Test
	public void edgesGraph_getCycleInEulerian_undirected_backForth() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(Sets.newHashSet(v1, v3), twoEdges, HashMultiset.<Edge>create());
		
		List<Vertex> cycle = graph.getCycleInEulerian(graph, v1);
		assertEquals(cycle, Lists.newArrayList(v1, v3));
	}
	
	@Test
	public void edgesGraph_getCycleInEulerian_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, undirectedEdgesForCycle, HashMultiset.<Edge>create());
		List<Vertex> cycle = graph.getCycleInEulerian(graph, v4);
		assertTrue((cycle.size() == 3) ||
				(cycle.size() == 5) ||
				(cycle.size() == 6) ||
				(cycle.size() == 7) ||
				(cycle.size() == 8) ||
				(cycle.size() == 9));
	}

	@Test
	public void edgesGraph_getCycleInEulerian_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, HashMultiset.<Edge>create(), directedEdgesForCycle);
		List<Vertex> cycle = graph.getCycleInEulerian(graph, v4);
		
		assertTrue((cycle.size() == 3) ||
				(cycle.size() == 5) ||
				(cycle.size() == 6) ||
				(cycle.size() == 7) ||
				(cycle.size() == 8) ||
				(cycle.size() == 9));
	}

	public Set<Vertex> smallVerticesSet = Sets.newHashSet(v1, v2, v3);
	public Multiset<Edge> smallUndirected = HashMultiset.create();
	@Before
	public void initializeSmallUndirected() {
		smallUndirected.add(e1u);
		smallUndirected.add(e2u);
		smallUndirected.add(e3u);
	}
	public Multiset<Edge> smallDirected = HashMultiset.create();
	@Before
	public void initializeSmallDirected() {
		smallDirected.add(e1d);
		smallDirected.add(e2d);
		smallDirected.add(e3d);
	}
	
	List<Vertex> smallCycle = Lists.newArrayList(v1, v2, v3);	
	List<Vertex> smallCycleOpposite = Lists.newArrayList(v1, v3, v2);
	
	@Test
	public void edgesGraph_getEulerianCycle_undirected_small() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(smallVerticesSet, smallUndirected, HashMultiset.<Edge>create());

		assertTrue(graph.getEulerianCycle(graph, v1).equals(smallCycle) ||
				graph.getEulerianCycle(graph, v1).equals(smallCycleOpposite));
	}
	
	@Test
	public void edgesGraph_getEulerianCycle_directed_small() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(smallVerticesSet, HashMultiset.<Edge>create(), smallDirected);
		
		assertTrue(graph.getEulerianCycle(graph, v1).equals(smallCycle));
	}
	
	// Test returns an error
	@Test
	public void edgesGraph_getEulerianCycle_undirected() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, undirectedEdgesForCycle, HashMultiset.<Edge>create());
		
		assertEquals(9, graph.getEulerianCycle(graph, v1).size());
	}
	
	// Test returns an error
	@Test
	public void edgesGraph_getEulerianCycle_directed() {
		EdgesGraph graph = edgesGraphFactory.createEdgesGraph(largerVerticesSet, HashMultiset.<Edge>create(), directedEdgesForCycle);
		
		assertEquals(9, graph.getEulerianCycle(graph, v1).size());
	}
	
	// TODO (method to do, too!)
	@Test
	public void edgesGraph_getCycle_undirected() {
		
	}
	
	// TODO (method to do, too!)
	@Test
	public void edgesGraph_getCycle_directed() {
		
	}
}
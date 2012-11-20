package graphimplementations;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
//import org.junit.experimental.theories.DataPoints;
//import org.junit.experimental.theories.Theories;
//import org.junit.experimental.theories.Theory;
//import org.junit.runner.RunWith;

import graphimplementations.Edge;
import graphimplementations.EdgesGraph;
import graphimplementations.Vertex;

public class EdgeTest {
	public EdgesGraph undirectedGraph = new EdgesGraph();
	public EdgesGraph directedGraph = new EdgesGraph();
	public EdgesGraph graph = new EdgesGraph();
	
	public Vertex v1 = new Vertex();
	public Vertex v2 = new Vertex();
	Set<Vertex> verticesUndirected = Sets.newHashSet(v1, v2);
	public Edge e1 = Edge.between(v1).and(v2);
	public Edge e2 = Edge.between(v2).and(v1);
	public Edge undirectedLoop = Edge.between(v1).and(v1);

	public Vertex v3 = new Vertex();
	public Vertex v4 = new Vertex();
	Set<Vertex> verticesDirected = Sets.newHashSet(v3, v4);
	public Edge a1 = Edge.from(v3).to(v4);
	public Edge a2 = Edge.from(v4).to(v3);
	public Edge directedLoop = Edge.from(v3).to(v3);	

	@Before
	public void constructUndirectedGraph() {
		undirectedGraph.addVertices(verticesUndirected);
		undirectedGraph.addEdge(e1);
		undirectedGraph.addEdge(e2);
		undirectedGraph.addEdge(undirectedLoop);
	}

	@Before
	public void constructDirectedGraph() {
		directedGraph.addVertices(verticesDirected);
		directedGraph.addEdge(a1);
		directedGraph.addEdge(a2);
	}

	@Before
	public void constructDirectedAndUndirectedGraph() {
		graph.addVertices(verticesUndirected);
		graph.addVertices(verticesDirected);
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(a1);
		graph.addEdge(a2);
		graph.addEdge(undirectedLoop);
		graph.addEdge(directedLoop);
	}
	
	@Test
	public void builder_undirected() {
		
		assertTrue(e1.equals(e2));
	}
	
	@Test
	public void builder_directed() {
		directedGraph.addEdge(a1);
		directedGraph.addEdge(a2);
		
		assertFalse(a1.equals(a2));
	}
	
	@Test
	public void builder_directedUndirected() {
		graph.addEdge(e1);
		graph.addEdge(a1);
		
		assertFalse(e1.equals(a1));
	}
	
	@Test
	public void isLoop_undirected() {		
		assertTrue(undirectedLoop.isLoop());
	}
		
	@Test
	public void isLoop_directed() {		
		assertTrue(directedLoop.isLoop());
	}

	@Test
	public void degree_undirected() {
		assertEquals(3, v1.getDegree());
	}

	@Test
	public void indegree_undirected() {
		assertEquals(0, v1.getIndegree());
	}

	@Test
	public void degree_directed() {
		assertEquals(0, v3.getDegree());
	}

	@Test
	public void indegree_directed() {
		assertEquals(1, v4.getIndegree());
	}

	@Test
	public void outdegree_directed() {
		assertEquals(2, v3.getIndegree());
	}
}

//@RunWith(Theories.class)
//public class EdgeTest {
//
//	private final static Vertex[] vertices = 
//		{ new Vertex(), new Vertex(), new Vertex() };
//
//	@DataPoints
//	public static Edge[] edges = 
//		{ new Edge(vertices[0], vertices[1]), 
//		new Edge(vertices[0], vertices[2]), 
//		new Edge(vertices[0], vertices[0]) };
//	@DataPoints
//	public static Edge[] directedEdges = 
//		{ new Edge(vertices[0], vertices[1], true), 
//		new Edge(vertices[0], vertices[2], true), 
//		new Edge(vertices[0], vertices[0], true) };
//	
//	
//	@Theory
//	public void testConstructorAndEquals(Edge e, Edge f) {
//		if (e != f) {
//			assertFalse(e.equals(f));			
//		} else {
//			assertTrue(e.equals(f));
//		}
//	}
//	
//	@Test
//	public void testDirectedNotUndirected() {
//		assertFalse(edges[0].equals(directedEdges[0]));
//	}
//
//	@Test 
//	public void testLoopUndirected() {
//		assertTrue(edges[2].isLoop());
//	}
//
//	@Test 
//	public void testLoopDirected() {
//		assertTrue(directedEdges[2].isLoop());
//	}
//}


//public class EdgeTest {
//	static Vertex v1 = new Vertex();
//	static Vertex v2 = new Vertex();
//		
//	@Test
//	public void testEqual_Undirected_true() {
//		Edge e = new Edge(v1, v2);
//		Edge f = new Edge(v2, v1);
//		assertTrue(e.equals(f));
//	}
//	
//	@Test
//	public void testEqual_Undirected_false() {
//		Edge e = new Edge(v1, v2);
//		Edge g = new Edge(v2, v2);
//		assertFalse(e.equals(g));
//	}
//
//	@Test
//	public void testEqual_Directed_true() {
//		Edge a = new Edge(v1, v2, true);
//		assertTrue(a.equals(a));
//	}
//	
//	@Test
//	public void testEqual_UndirectedDirected_false() {
//		Edge a = new Edge(v1, v2, true);
//		Edge e = new Edge(v1, v2);
//		assertFalse(a.equals(e));
//	}
//	
//	@Test
//	public void testEqual_Directed_false() {
//		Edge a = new Edge(v1, v2, true);
//		Edge b = new Edge(v2, v1, true);
//		assertFalse(a.equals(b));
//	}
//	
//	@Test
//	public void testIsLoop_Undirected_true() {
//		Edge g = new Edge(v1, v1);
//		assertTrue(g.isLoop());
//	}
//
//	@Test
//	public void testIsLoop_Directed_true() {
//		Edge c = new Edge(v1, v1, true);
//		assertTrue(c.isLoop());
//	}
//	
//	@Test
//	public void testGetEdgesAt() {
//		Edge a = new Edge(v1, v2);
//		assertTrue(v1.getEdgesAt().contains(a));
//	}
//}

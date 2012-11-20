package graphimplementations;

import static org.junit.Assert.*;

import graphimplementations.Edge;
import graphimplementations.Vertex;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

@RunWith(Theories.class)
public class VertexTest {

	@DataPoints
	public static Vertex[] VERTICES = { new Vertex(), new Vertex(), new Vertex() };
	
	@Theory
	public void getLabel_neverSameLabel(Vertex v, Vertex w) {
		if (v != w) {
			assertFalse(v.label == w.label);			
		} else {
			assertTrue(v.label == w.label);
		}
	}

    @Theory
    public void equals_neverEqual(Vertex v, Vertex w) {
        if (v.label != w.label) {
            assertFalse(v.equals(w));           
        } else {
            assertTrue(v.equals(w));
        }
    }

    // test edges at vertex methods (indegree/outdegree), undirected edges.
    public Vertex v1 = new Vertex();
    public Vertex v2 = new Vertex();
    public Vertex v3 = new Vertex();
    public Edge e1 = Edge.between(v1).and(v2);
    public Edge e2 = Edge.between(v1).and(v3);
    public Edge e3 = Edge.between(v1).and(v3);
    
    @Test
    public void getEdges_true() {    	
    	Multiset<Edge> edges = HashMultiset.create();
    	edges.add(e1);
    	edges.add(e2);
    	edges.add(e3);
    	
    	assertEquals(edges, v1.getEdges());
    }
    
    @Test
    public void getDegree_true() {
    	assertEquals(3, v1.getDegree());
    }

    // test edges at vertex methods (indegree/outdegree), directed edges.
    public Vertex v4 = new Vertex();
    public Vertex v5 = new Vertex();
    public Vertex v6 = new Vertex();
    public Edge a1 = Edge.from(v4).to(v5);
    public Edge a2 = Edge.from(v4).to(v6);
    public Edge a3 = Edge.from(v5).to(v5);
    
    @Test
    public void getIndegree_true() {
    	assertEquals(0, v4.getIndegree());
    }

    @Test
    public void getOutdegree_true() {
    	assertEquals(2, v4.getOutdegree());    	
    }
    
    @Test
    public void getIndegree_withLoop() {
    	assertEquals(2, v5.getIndegree());
    }

    // undirected edges give no indegree (or outdegree)
    @Test
    public void getIndegree_zeroInUndirectedGraph() {
    	assertEquals(0, v1.getIndegree());
    }
}

//public class VertexTest {
//	static Vertex v1 = new Vertex();
//	static Vertex v2 = new Vertex();
//	
//	@Test
//	public void testConstructorLabel1() {		
//		assertTrue(v1.getLabel() == 1);
//	}
//	
//	@Test
//	public void testConstructorLabel2() {
//		assertTrue(v2.getLabel() == 2);
//	}
//
//	@Test
//	public void testGetNoOfVertices() {
//		assertTrue(Vertex.getNoOfVertices() == 2);
//	}
//
//	@Test
//	public void testConstructorEdgesAt() {
//		Multiset<Edge> edges = HashMultiset.create();
//		Vertex v3 = new Vertex(edges);
//		edges.add(new Edge(v1, v3));
//		edges.add(new Edge(v2, v3));
//		
//		assertTrue(v3.getEdgesAt().equals(edges));
//	}
//
//	@Test
//	public void testResetLabel() {
//		Vertex.resetLabel();
//		Vertex v = new Vertex();
//		assertTrue(v.getLabel() == 1);
//	}
//
//}

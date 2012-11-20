package graphimplementations;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;

import interfaces.VertexInterface;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement a vertex of a graph.
 *
 */
public class Vertex implements VertexInterface {

	private static int noOfVertices;
	@VisibleForTesting int label;
	private Multiset<Edge> edges;

	public Vertex() {
		this.label = getNextLabel();
		this.edges = HashMultiset.create(); 
	}

	private synchronized static int getNextLabel() {
		return noOfVertices++;
	}

	@Override
	public Multiset<Edge> getEdges() {
		return this.edges;
	}	

	@Override
	public int hashCode() {
		return Objects.hashCode(label);
	}	

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex otherVertex = (Vertex) obj;
		return Objects.equal(this.label, otherVertex.label)
				&& Objects.equal(this.edges, otherVertex.edges);
		// why not this.label == otherVertex.label?
		// because we want to consider the hashcode as well?
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
/**
 * 	@return number of edges incident at vertex (directed and undirected) 	
 */	
	public int getNoOfEdges() {
		return getEdges().size();
	}

/**
 * 	@return degree at vertex (directed edges are not counted)	
 */	
	@Override
	public int getDegree() {
		return getDirectedDegree(isUndirectedAt(this));
	}

/**
 * 	@return indegree at vertex	
 */	
	@Override
	public int getIndegree() {
		return getDirectedDegree(isInEdgeTo(this));
	}

/**
 * 	@return outdegree at vertex	
 */
	@Override
	public int getOutdegree() {
		return getDirectedDegree(isOutEdgeFrom(this));
	}

/**
 * 
 * @param directionPredicate
 * @return edges incident at vertex that satisfy the predicate
 */
	private int getDirectedDegree(Predicate<Edge> directionPredicate) {
		return ImmutableList.copyOf(Iterables.filter(getEdges(), directionPredicate)).size();
	}

/**
 * 
 * @param vertex
 * @return directed edges that contribute to indegree at vertex
 */
	private static Predicate<Edge> isInEdgeTo(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (edge.isDirected() && edge.getEnd().equals(vertex));
			}
		};
	}

/**
 * 
 * @param vertex
 * @return directed edges that contribute to outdegree at vertex
 */
	private static Predicate<Edge> isOutEdgeFrom(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (edge.isDirected() && edge.getStart().equals(vertex));
			}
		};
	}

/**
 * 
 * @param vertex
 * @return undirected edges at vertex
 */	
	private static Predicate<Edge> isUndirectedAt(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (!edge.isDirected() && 
						(edge.getStart().equals(vertex) || edge.getEnd().equals(vertex)));
			}
		};
	}
}


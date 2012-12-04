package graphimplementations;

import com.google.common.base.Objects;

import interfaces.EdgeInterface;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement an edge (directed or undirected) of a graph.
 *
 */

public class Edge implements EdgeInterface {

	public static class UndirectedBuilder {

		private final Vertex start;
		private UndirectedBuilder(Vertex s) {
			this.start = s;
		}
		
		public Edge and(Vertex end) {
			Edge e = new Edge(start, end, false);
			start.addEdge(e);
			if (!start.equals(end)) {
				end.addEdge(e);				
			}
			return e;
		}
	}

	public static class DirectedBuilder {

		private final Vertex start;
		private DirectedBuilder(Vertex s) {
			this.start = s;
		}
		
		public Edge to(Vertex end) {
			Edge e = new Edge(start, end, true);
			start.addEdge(e);
			if (!start.equals(end)) {
				end.addEdge(e);				
			} 
			return e;
		}
	}

	private final Vertex start;
	private final Vertex end;
	private final boolean isDirected;	
	
	private Edge(Vertex i, Vertex j, boolean dir) {
		this.start = i;
		this.end = j;
		this.isDirected = dir;
	}

	public static UndirectedBuilder between(Vertex start) {
		return new UndirectedBuilder(start);
	}
	
	public static DirectedBuilder from(Vertex start) {
		return new DirectedBuilder(start);
	}
	
	@Override
	public Vertex getStart() {
		return start;
	}

	@Override
	public Vertex getEnd() {
		return end;
	}

	@Override
	public boolean isDirected() {
		return isDirected;
	}
	
	@Override
	public int hashCode() {
		if (isDirected()) {
			return Objects.hashCode(start, end);
		}
		return Objects.hashCode(start, end) + Objects.hashCode(end, start);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}		
		Edge otherEdge = (Edge) obj;
		if (this.isDirected() != otherEdge.isDirected()) {
			return false;
		}
		if (this.isDirected()) {
			if (otherEdge.start.equals(this.start) && otherEdge.end.equals(this.end)) {
				return true;
			} 
			return false;
		} else {
			if ((otherEdge.start.equals(this.start) && otherEdge.end.equals(this.end)) || 
					(otherEdge.start.equals(this.end) && otherEdge.end.equals(this.start))) {
				return true;
			}
			return false;			
		}
	}
	
	@Override
	public boolean isLoop() {
		return start.equals(end);
	}
}
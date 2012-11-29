package graphimplementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import abstractclasses.AbstractGraph;

/**
 * 
 * @author mmcasetti
 * 
 * A class to implement a multigraph, seen as a collection of vertices 
 * and edges (possibly repeated) between these.
 *
 */

public class EdgesGraph extends AbstractGraph {
	
	private Set<Vertex> vertices;
	private Multiset<Edge> edges;
	private Multiset<Edge> directedEdges;
	
	public EdgesGraph(Set<Vertex> v, Multiset<Edge> e, Multiset<Edge> d) {
		for (Edge edge : e) {
			Preconditions.checkArgument(v.contains(edge.getStart()) 
					&& v.contains(edge.getEnd()),
					"Endpoints of edges not in vertices");
		}
		// TODO edges/directedEdges well defined (no directed edge a,b 
		// and directed edge b,a - but undirected edge a,b)
		
		this.vertices = v;
		this.edges = e;
		this.directedEdges = d;
	}
	
	public EdgesGraph() {
		this.vertices = Sets.newHashSet();
		this.edges = HashMultiset.create();
		this.directedEdges = HashMultiset.create();
	}
	
	@Override
	public Set<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public Multiset<Edge> getEdges() {
		return edges;
	}
	
	@Override
	public Multiset<Edge> getDirectedEdges() {
		return directedEdges;
	}
	
	@Override
	public boolean isDirected() {
		return (directedEdges.size() > 0);
	}
	
	public int getNoOfVertices() {
		return getVertices().size();
	}
	
	public EdgesGraph copy() {
		Set<Vertex> v = Sets.newHashSet(this.getVertices());
		Multiset<Edge> e = HashMultiset.create(this.getEdges());
		Multiset<Edge> d = HashMultiset.create(this.getDirectedEdges());
		return new EdgesGraph(v, e, d);
	}

	@Override
	public Multiset<Edge> getEdgesAt(Vertex vertex) {
		return HashMultiset.create(getDirectedDegree(isUndirectedAt(vertex)));
	}
	
	@Override
	public Multiset<Edge> getEdgesFrom(Vertex vertex) {		
		return HashMultiset.create(getDirectedDegree(isOutEdgeFrom(vertex)));
	}
	
	@Override
	public Multiset<Edge> getEdgesTo(Vertex vertex) {		
		return HashMultiset.create(getDirectedDegree(isInEdgeTo(vertex)));
	}

	private Multiset<Edge> getDirectedDegree(Predicate<Edge> directionPredicate) {
		return HashMultiset.create(Iterables.filter(getEdges(), 
				directionPredicate));
	}
	
	private static Predicate<Edge> isUndirectedAt(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (!edge.isDirected() && (edge.getStart().equals(vertex) || 
						edge.getEnd().equals(vertex)));
			}
		};
	}
	
	private static Predicate<Edge> isOutEdgeFrom(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (edge.isDirected() && edge.getStart().equals(vertex));
			}
		};
	}

	private static Predicate<Edge> isInEdgeTo(final Vertex vertex) {
		return new Predicate<Edge>() {
			@Override
			public boolean apply(Edge edge) {
				return (edge.isDirected() && edge.getEnd().equals(vertex));
			}
		};
	}

	public int getDegreeAt(Vertex vertex) {
		return getEdgesAt(vertex).size();
	}

	@Override
	public int getOutdegreeAt(Vertex vertex) {
		return getEdgesFrom(vertex).size();
	}
	
	@Override
	public int getIndegreeAt(Vertex vertex) {
		return getEdgesTo(vertex).size();
	}
	
	@Override
	public void addVertices(Set<Vertex> newVertices) {
		for (Vertex v : newVertices) {
			Preconditions.checkArgument(!vertices.contains(v), 
					"New vertex already in graph.");
		}
		
		Set<Vertex> newSet = new HashSet<Vertex>(getVertices());
		newSet.addAll(newVertices);
		vertices = newSet;
	}
	
	@Override
	public void removeVertex(Vertex vertex) {
		Preconditions.checkArgument(vertices.contains(vertex), 
				"Vertex not in graph.");

		Multiset<Edge> newEdges = HashMultiset.create();
		Multiset<Edge> newDirectedEdges = HashMultiset.create();
		
		for (Edge e : getEdges()){
			if (!e.getStart().equals(vertex) && !e.getEnd().equals(vertex)) {
				newEdges.add(e);
			} 
		}
		for (Edge e : getDirectedEdges()){
			if (!e.getStart().equals(vertex) && !e.getEnd().equals(vertex)) {
				newDirectedEdges.add(e);
			} 
		}
		edges = newEdges;
		directedEdges = newDirectedEdges;

		Set<Vertex> newVertices = new HashSet<Vertex>(getVertices());
		newVertices.remove(vertex);
		vertices = newVertices;
	}
	
	@Override
	public void addEdge(Edge edge){
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(!edge.isDirected(), "Use addDirectedEdge.");

		edges.add(edge);			
	}

	@Override
	public void addDirectedEdge(Edge edge){
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(edge.isDirected(), "Use addEdge");

		Edge opposite = Edge.from(edge.getEnd()).to(edge.getStart());		
		if (!directedEdges.contains(opposite)) {
			directedEdges.add(edge);						
		} else {
			Edge undirected = Edge.between(edge.getStart()).and(edge.getEnd());		
			directedEdges.remove(opposite);
			edges.add(undirected);
		}
	}

	@Override
	public void addEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), "Edge's endpoints not in graph.");
		
		Edge edge = Edge.between(start).and(end);
		edges.add(edge);
	}

	@Override
	public void addDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), "Edge's endpoints not in graph.");

		Edge edge = Edge.from(start).to(end);
		Edge opposite = Edge.from(end).to(start);
		if (!directedEdges.contains(opposite)) {
			directedEdges.add(edge);
		} else {
			Edge undirected = Edge.between(start).and(end);
			directedEdges.remove(opposite);
			edges.add(undirected);
		}
	}

	@Override
	public void removeEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Preconditions.checkArgument(edges.contains(edge), 
				"Edge not in graph.");
		Preconditions.checkArgument(!edge.isDirected(), "Use removeDirectedEdge.");
		
		edges.remove(edge);
	}

	@Override
	public void removeDirectedEdge(Edge edge) {
		Preconditions.checkArgument(vertices.contains(edge.getStart()) 
				&& vertices.contains(edge.getEnd()), 
				"Edge's endpoints not in graph.");
		Edge undirected = Edge.between(edge.getStart()).and(edge.getEnd());
		Preconditions.checkArgument(directedEdges.contains(edge) || edges.contains(undirected), 
				"Edge not in graph.");
		Preconditions.checkArgument(edge.isDirected(), "Use removeEdge.");
		
		if (directedEdges.contains(edge)) {
			directedEdges.remove(edge);
		} else {
			Edge opposite = Edge.from(edge.getEnd()).to(edge.getStart());
			edges.remove(undirected);
			directedEdges.add(opposite);
		}
	}

	@Override
	public void removeEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.between(start).and(end);
		Preconditions.checkArgument(edges.contains(edge), 
				"Edge not in graph.");
		
		edges.remove(edge);
	}

	@Override
	public void removeDirectedEdge(Vertex start, Vertex end) {
		Preconditions.checkArgument(vertices.contains(start) 
				&& vertices.contains(end), 
				"Edge's endpoints not in graph.");
		Edge edge = Edge.from(start).to(end);
		Edge undirected = Edge.between(start).and(end);
		Preconditions.checkArgument(directedEdges.contains(edge) ||
				edges.contains(undirected), "Edge not in graph.");
		
		if (directedEdges.contains(edge)){
			directedEdges.remove(edge);			
		} else {
			Edge opposite = Edge.from(end).to(start);
			edges.remove(undirected);
			directedEdges.add(opposite);
		}
	}

	public boolean isEulerian() {
		if (!this.isDirected()) {
			for (Vertex v : getVertices()) {
				if (getDegreeAt(v)%2 != 0) {
					return false;
				}
// 				Alternatively...
//				boolean evenDegree = true;
//				for (Edge edge : getEdgesAt(v)) {
//					if (!edge.isLoop()){
//						evenDegree = !evenDegree;
//					} 
//				}
//				if (evenDegree == false){
//					return false;
//				}
			}
			return true;			
		} else {
			// this is the general case, we could also skip the if/else
			// but on the other hand if the graph is not directed
			// it's useless to check in/outdegree for each vertex
			for (Vertex v : getVertices()) {
				if ((getOutdegreeAt(v) != getIndegreeAt(v)) 
						|| getDegreeAt(v)%2 != 0) {
					return false;
				}
			}
			return true;
		}
	}

	public boolean isPerfectMatching(Multiset<Edge> subset) {
		for (Edge e : subset) {
			Preconditions.checkArgument(edges.contains(e) 
					|| directedEdges.contains(e), "Edge of subset not in graph.");
		}
		// if there is an odd no. of vertices, no perfect matching
		if (getNoOfVertices()%2 == 1){
			return false;
		}
		// else check that there are exactly noOfVertices/2 edges
		if (subset.size() != getNoOfVertices()/2){
			return false;
		}
		// check that each vertex is covered (this will imply that it is covered
		// once, since the n. of edges in subset is noOfVertices/2)
		boolean isCovered;
		
		for (Vertex i : getVertices()){
			isCovered = false;
			for (Edge edge : subset) {
				// we do not need to use a xor to avoid loops 
				// because we already know that subset.size() == noOfVertices/2 
				if (edge.getStart().equals(i) || edge.getEnd().equals(i)) {
					if (isCovered) {
						return false;
					}
					isCovered = true;
				}
			}
			if (!isCovered) {
				return false;
			}
		}						
		return true;
	}

	// TODO from here it is still with only undirected graphs	
	
	public List<Vertex> getEulerianCycle(EdgesGraph graph, Vertex startNode) {
		Preconditions.checkArgument(vertices.contains(startNode), "Start node not in graph");
		Preconditions.checkArgument(graph.isEulerian(), "Graph not Eulerian.");
		
		final EdgesGraph copyOfGraph = graph.copy();
		
		List<Vertex> eulerianCycle = findAndRemoveCycle(copyOfGraph, startNode);
		
		while (!copyOfGraph.getEdges().isEmpty()) {
			Vertex junction = Iterables.find(eulerianCycle, new Predicate<Vertex>() {
				@Override
				public boolean apply(Vertex vertex) {
					return copyOfGraph.getDegreeAt(vertex) > 0;
				}
			});
			List<Vertex> cycle = findAndRemoveCycle(copyOfGraph, junction);
			eulerianCycle = graph.mergeTours(Optional.of(eulerianCycle), Optional.of(cycle), junction);
		}
		return eulerianCycle;
	}

	private List<Vertex> findAndRemoveCycle(EdgesGraph edgesGraph, Vertex startNode) {
		List<Vertex> cycle = Lists.newArrayList(
				edgesGraph.getCycle(edgesGraph, startNode).get());
		for (Integer i = 0; i < cycle.size() - 1; i++) {
			edgesGraph.removeEdge(cycle.get(i), cycle.get(i + 1));
		}
		edgesGraph.removeEdge(cycle.get(cycle.size() - 1), cycle.get(0));
		return cycle;
	}
	
	public Optional<List<Vertex>> getCycle(EdgesGraph edgesGraph, Vertex startNode) {
		Preconditions.checkArgument(vertices.contains(startNode), "Start node not in graph");

		List<Vertex> cycle = Lists.newArrayList();		
		Vertex currentNode = startNode;
		EdgesGraph copyOfGraph = edgesGraph.copy();
		
		do {
			cycle.add(currentNode);
			Multiset<Edge> edgesAt = copyOfGraph.getEdgesAt(currentNode);
			if (edgesAt.isEmpty()) {
				 return Optional.<List<Vertex>>absent();
			}
			// we take the last element in edgesAt - any edge is ok;
			// we already know (previous clause) that edgesAt is not empty.
			Edge edgeFromCurrentNode = Iterables.getLast(edgesAt);
			Vertex nextNode = (edgeFromCurrentNode.getStart().equals(currentNode))
				? edgeFromCurrentNode.getEnd()
				: edgeFromCurrentNode.getStart();
			
			copyOfGraph.removeEdge(currentNode, nextNode);
			currentNode = nextNode;
		} while (!currentNode.equals(startNode));
		
		Optional<List<Vertex>> c = Optional.of(cycle);
		return c;
	}

	public List<Vertex> mergeTours(Optional<List<Vertex>> tour1, 
			Optional<List<Vertex>> tour2, Vertex junction) {		
		List<Vertex> tour3 = Lists.newArrayList();
		
		if (!tour1.isPresent() && !tour2.isPresent()) {
			return tour3; 
		} else if (!tour1.isPresent()) {
			return tour2.get();
		} else if (!tour2.isPresent()) {
			return tour1.get();
		}

		Preconditions.checkArgument(vertices.contains(junction), "Junction not in graph");
		Preconditions.checkArgument(tour1.get().contains(junction), "Junction not in tour1");
		Preconditions.checkArgument(tour2.get().contains(junction), "Junction not in tour2");
		Preconditions.checkArgument(vertices.containsAll(tour1.get()) && vertices.containsAll(tour2.get()),
				"Tours not a subset of vertices");

		boolean tour2HasBeenVisited = false;
		for (Vertex tour1Vertex : tour1.get()) {
			tour3.add(tour1Vertex);
			if (!tour2HasBeenVisited && tour1Vertex.equals(junction)) {
				int indexOfJunctionInTour2 = tour2.get().indexOf(junction);
				for (int i = indexOfJunctionInTour2 + 1; i < tour2.get().size(); i++) {
					Vertex tour2Vertex = tour2.get().get(i % tour2.get().size());
					tour3.add(tour2Vertex);
				}
				tour2HasBeenVisited = true;
			}
		}
		return tour3;
	}
}
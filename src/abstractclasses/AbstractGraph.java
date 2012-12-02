package abstractclasses;

import interfaces.Graph;

public abstract class AbstractGraph implements Graph {
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof AbstractGraph)) {
			return false;
		}
			
		AbstractGraph otherGraph = (AbstractGraph) other;
		if (this.isDirected() != otherGraph.isDirected()) {
			return false;
		}
		if (!this.getVertices().equals(otherGraph.getVertices())){
			return false;
		}
		if (!this.getUndirectedEdges().equals(otherGraph.getUndirectedEdges())) {
			return false;
		}
		if (!this.getDirectedEdges().equals(otherGraph.getDirectedEdges())) {
			return false;
		}
		
		return true;
	}
}

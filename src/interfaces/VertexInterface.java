package interfaces;

import graphimplementations.Edge;


import com.google.common.collect.Multiset;

public interface VertexInterface {
	public Multiset<Edge> getEdges();

	public boolean equals(Object o);

	public int getDegree();
	public int getIndegree();
	public int getOutdegree();
}
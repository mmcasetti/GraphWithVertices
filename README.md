*graph with vertices*

A graph can be seen as a collection of vertices and edges (directed or 
undirected, possibly repeated) between these vertices, or as an adjacency 
matrix (a matrix that has as entry at i,j the number of edges between the 
vertex i and the vertex j), or as an adjacency list (a list of the vertices; 
for each vertex, a multiset of the vertices connected with an edge to it).

The inheritance is: 
VertexInterface -> Vertex; 
EdgeInterface -> Edge; 
Graph -> AbstractGraph -> EdgesGraph, MatrixGraph, ListGraph.
The graph can be built using one of the factories: EdgesGraphFactory, 
MatrixGraphFactory, ListGraphFactory.
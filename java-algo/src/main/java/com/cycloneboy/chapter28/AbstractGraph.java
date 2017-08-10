/**
 * 
 */
package com.cycloneboy.chapter28;

import java.util.*;



/**
 * @author CycloneBoy
 *
 */
public abstract class AbstractGraph<V> implements Graph<V>{
	protected List<V>  vertices = new ArrayList<V>();//Store vertices
	protected List<List<Edge>> neighbors = new ArrayList<List<Edge>>();//Adjacency lists
	
	/** 
	 * Construct an empty graph 
	 */
	public AbstractGraph() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construct a graph from vertices and edges stored in arrays
	 */
	protected AbstractGraph(V[] vertices,int [][] edges){
		for(int i = 0; i < vertices.length;i++){
			addVertex(vertices[i]);
		}
		
		createAdjacencyLists(edges, vertices.length);
	}
	
	/**
	 * Construct a graph from vertices and edges stored in List
	 */
	protected AbstractGraph(List<V> vertices, List<Edge> edges){
		for (int i = 0; i < vertices.size(); i++) {
			addVertex(vertices.get(i));
		}
		
		createAdjacencyLists(edges, vertices.size());
	}
	
	/**
	 * Create adjacency lists for each vertex
	 */
	private void createAdjacencyLists(
		int[][] edges,int numberOfVertices) {
		for(int i = 0; i < edges.length; i++){
			addEdge(edges[i][0], edges[i][1]);
		}
	}
	
	/**
	 * Construct a graph for integer vertices 0, 1, 2, and edge list 
	 */
	protected AbstractGraph(List<Edge> edges , int numberOfVertices){
		for(int i = 0; i< numberOfVertices;i++){
			addVertex((V)(new Integer(i))); //vertices is {0, 1, ....}
		}
		
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * Construct a graph from integer vertices 0,1, and edge array
	 */
	protected AbstractGraph(int[][] edges, int numberOfVertices){
		for(int i = 0;i < numberOfVertices;i++){
			addVertex((V)(new Integer(i)));
		}
		
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * Create adjacency lists for each vertex
	 */
	private void createAdjacencyLists(
		List<Edge>	edges, int numberOfVertices){
		for (Edge edge : edges) {
			addEdge(edge.u, edge.v);
		}
	}
	

	/**
	 * Return the number of vertices in the graph
	 */
	public int getSize() {
		return vertices.size();
	}
	

	/**
	 * Return the vertices in the graph
	 */
	public List<V> getVertices() {
		return vertices;
	}


	/**
	 * Return the object for the specified vertex
	 */
	public V getVertex(int index) {
		return vertices.get(index);
	}


	/**
	 * Return the index for the specified vertex
	 */
	public int getIndex(V v) {
		return vertices.indexOf(v);
	}


	/**
	 * Return the neighbors of the specified vertex
	 */
	public List<Integer> getNeighbors(int index) {
		List<Integer> result = new ArrayList<Integer>();
		for(Edge e: neighbors.get(index)){
			result.add(e.v);
		}
		
		return result;
	}
	

	/**
	 * Return the degree for a specified vertex
	 */
	public int getDegree(int v) {
		return neighbors.get(v).size();
	}


	/**
	 * Print the edges
	 */
	public void printEdges() {
		for(int u = 0;u < neighbors.size(); u++){
			System.out.print(getVertex(u) + " ("+ u + ")");
			for(Edge e : neighbors.get(u)){
				System.out.print("(" + getVertex(e.u) + ", " +
						getVertex(e.v) + ") ");
			}
			System.out.println();
		}
	}


	/**
	 * Clear the graph
	 */
	public void clear() {
		vertices.clear();
		neighbors.clear();
	}


	/**
	 * Add a vertex to the graph
	 */
	public boolean addVertex(V vertex) {
		if(!vertices.contains(vertex)){
			vertices.add(vertex);
			neighbors.add(new ArrayList<Edge>());
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Add an edge to the graph
	 */
	protected boolean addEdge(Edge e ){
		if (e.u < 0 || e.u > getSize() - 1) {
			throw new IllegalArgumentException("No such index: " + e.u);
		} 
		
		if (e.v  < 0 || e.v > getSize() - 1) {
			throw new IllegalArgumentException("No such index: " + e.v);
		} 
		
		if(!neighbors.get(e.u).contains(e)){
			neighbors.get(e.u).add(e);
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * Add an edge to the graph
	 */
	public boolean addEdge(int u, int v) {
		return addEdge(new Edge(u, v));
	}
	
	/**Edge inner class inside the AbstractGraph class*/
	 public static class Edge{
		public int u;//Staring vertex of the edge
		public int v;//Ending vertex of the edge
		
		/**Construct an edge for(u,v)*/
		public Edge(int u, int v) {
			super();
			this.u = u;
			this.v = v;
		}
		
		public boolean equals(Object o){
			return u == ((Edge)o).u && v == ((Edge)o).v;
		}
	}
	 

	 /**Obtain a depth-first search tree starting from v*/
		public Tree dfs(int v){
		 List<Integer> searchOrder = new  ArrayList<Integer>();
		 int [] parent = new int[vertices.size()];
		 for(int i = 0; i < parent.length;i++){
			 parent[i] = -1;	//Initialize parent[i] to -1
		 }
		 
		 //Mark visited vertices
		 boolean[] isVisted = new boolean[vertices.size()];
		 
		 //Recursively search
		 dfs(v,parent,searchOrder,isVisted);
		 
		 //Return a search tree
		 return new Tree(v, parent, searchOrder);
	 }
		
	 /**Recursive method for DFS search*/
	 private void dfs(int u, int[] parent,List<Integer> searchOrder,
			 boolean[] isVisited){
		  //Store the visited vertex
		 searchOrder.add(u);
		 isVisited[u] = true; //Vertex is visited
		 
		 for(Edge e : neighbors.get(u)){
			 if (!isVisited[e.v]) {
				parent[e.v] = u;//The parent of vertex e.v is u
				dfs(e.v, parent, searchOrder, isVisited);//Recursive search
			}
		 }
	 }
	 

	/**Obtain a breadth-first search tree starting from v*/
		public Tree bfs(int v){
		 List<Integer> searchOrder = new ArrayList<Integer>();
		 int[] parent = new int[vertices.size()];
		 for(int i = 0;i< parent.length ;i++){
			 parent[i] = -1;  //Initialize parent[i] to -1
		 }
		 
		 LinkedList<Integer> queue = new LinkedList<Integer>();//list used as a queue
		 boolean[] isVisited = new boolean[vertices.size()];
		 queue.offer(v); //Enqueue v
		 isVisited[v] = true; //Mark it visited
		 
		 while(!queue.isEmpty()){
			 int u = queue.poll();//Dequeue to u
			 searchOrder.add(u);
			 for(Edge e : neighbors.get(u)){
				 if(!isVisited[e.v]){
					 queue.offer(e.v);//Enqueue w
					 parent[e.v] = u; //The parent of w is u
					 isVisited[e.v] = true ;
				 }
			 }
		 }
		 
		 return new Tree(v, parent, searchOrder);
	 }
	 
	/**To be discussed in Section 28.6*/
	 public class Tree{
		 private int root;//The root of the tree
		 private int[] parent;//Store the parent of each vertex
		 private List<Integer> searchOrder; //Store the searchOrder
		
		 /**Construct a tree with root,parent, and searchOrder*/
		 public Tree(int root, int[] parent, List<Integer> searchOrder) {
			super();
			this.root = root;
			this.parent = parent;
			this.searchOrder = searchOrder;
		}

		 /**Return the root of the tree*/
		public int getRoot() {
			return root;
		}

		/**Return parent of vertex v*/
		public int getParent(int v) {
			return parent[v];
		}

		/**Return an array representing search ordrer*/
		public List<Integer> getSearchOrder() {
			return searchOrder;
		}
		
		/**Return number of vertices found*/
		public int getNumberOfVerticesFound(){
			return searchOrder.size();
		}
		
		/**Return the path of vertices from a vertex to the root*/
		public List<V> getPath(int index){
			ArrayList<V> path = new ArrayList<V>();
			
			do{
				path.add(vertices.get(index));
				index = parent[index];
			}while(index != -1);
			
			return path;
		}
		 
		/**Print a path from the root to vertex v*/
		public void printPath(int index){
			List<V> path = getPath(index);
			System.out.print("A path from " + vertices.get(root) + " to " + 
					vertices.get(index) + " : ");
			for(int i = path.size() - 1;i >= 0 ;i--){
				System.out.print(path.get(i) + " ");
			}
		}
		
		/**Print the whole tree*/
		public void printTree(){
			System.out.println("Root is: " + vertices.get(root));
			System.out.print("Edges: ");
			for(int i = 0;i < parent.length ; i++ ){
				if(parent[i] != -1){
					//Display an edge
					System.out.print("(" + vertices.get(parent[i]) + ", " +
							vertices.get(i) + ")");
				}
				System.out.println();
			}
		}
	 }
}

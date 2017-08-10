/**
 * 
 */
package com.cycloneboy.chapter28;

import java.util.*;


/**
 * @author CycloneBoy
 *
 */
public class NineTailModel {
	public final static int NUMBER_OF_NODES = 512;
	protected AbstractGraph<Integer>.Tree tree ;//Define a tree
	
	/**Construct a model*/
	public NineTailModel(){
		//Create edges
		List<AbstractGraph.Edge> edges = getEdges();
		
		//Create a graph
		UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges
				,NUMBER_OF_NODES);
		
		//Obtain a BFS tree rooted at the target node
		tree = graph.bfs(511);
	}
	
	/** Create all edges for the graph */
	
	private List<AbstractGraph.Edge> getEdges(){
		 List<AbstractGraph.Edge> edges = new  ArrayList<>();//Store edges
		 for(int u = 0;u < NUMBER_OF_NODES;u++){
		   		for(int k = 0;k < 9;k++){
		   			char[] node = getNode(u);//Get the node for vertex u
		   			if(node[k] == 'H'){
		   				int v = getFlippedNode(node,k);
		   				//Add edge(u,v) for a legal move from node v to node u
		   				edges.add(new AbstractGraph.Edge(v, u)); 
		   			}
		   		}
		   	}
		 return edges;
	}
	
   	public static int getFlippedNode(char[] node ,int position){
   		int row = position / 3;
   		int colum = position % 3;
   		
   		flipACell(node,row,colum);
   		flipACell(node,row - 1,colum);
   		flipACell(node,row + 1,colum);
   		flipACell(node,row,colum - 1);
   		flipACell(node,row,colum + 1);
   		
   		return getIndex(node);
   	}
			
	public static void flipACell(char[] node,int row, int colum){
		if(row >= 0 && row <= 2 && colum >= 0 && colum <= 2){
			//Within the boundary
			if(node[row*3 + colum] == 'H'){
				node[row*3 + colum] = 'T'; //Flip  form H to T
			}else {
				node[row*3 + colum] = 'H';//Flip form T to H
			}
		}
	}
	
	public static int getIndex(char[] node){
		int result = 0;
		
		for(int i = 0 ;i < 9; i++){
			if(node[i] == 'T'){
				result = result * 2 + 1;
			}else {
				result = result * 2 + 0;
			}
		}
		
		return result;
	}
	
	public static char[] getNode(int index){
		char[] result = new char[9];
		
		for (int i = 0; i < 9; i++) {
			int digit = index % 2;
			if(digit == 0){
				result[8-i] = 'H';
			}else{
				result[8-i] = 'T';
			}
			index = index / 2;
		}
		
		return result;
	}
	
	public List<Integer> getShortestPath(int nodeIndex){
		return tree.getPath(nodeIndex);
	}
	
	public static void printNode(char[] node) {
		for (int i = 0; i < 9; i++) {
			if(i % 3 != 2){
				System.out.print(node[i]);
			}else {
				System.out.println(node[i]);
			}
		}
		
		System.out.println();
	}
	
	public static void main(String[] args){
		//printNode("THHHHHHTT".toCharArray());
//		char[] node = getNode(259);
//		printNode(node);
		char[] node="HHHHHHHTT".toCharArray();
		System.out.println(getIndex(node));
		printNode(node);
	}
}

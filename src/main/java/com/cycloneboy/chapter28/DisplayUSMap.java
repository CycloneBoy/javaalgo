/**
 * 
 */
package com.cycloneboy.chapter28;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author CycloneBoy
 *
 */
public class DisplayUSMap extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		City[] vertices = { new City("Seattle",75,50),
				new City("SanFrancisco", 50, 210),
				new City("Los Angeles", 75, 275),new City("Denver", 275, 175),
				new City("Kansas City", 400, 245),
				new City("Chicago", 450, 100),new City("Boston", 700, 80),
				new City("New York", 675, 120),new City("Atlabta", 575, 295),
				new City("Miami", 600, 400),new City("Dallas", 408, 325),
				new City("Houston", 450, 360)};
		
		//Edge array for graph in Figure 28.1
				int[][] edges = { 
						{0,1},{0,3},{0,5},
						{1,0},{1,2},{1,3},
						{2,1},{2,3},{2,4},{2,10},
						{3,0},{3,1},{3,2},{3,4},{3,5},
						{4,2},{4,3},{4,5},{4,7},{4,8},{4,10},
						{5,0},{5,3},{5,4},{5,6},{5,7},{5,8},
						{6,5},{6,7},
						{7,4},{7,5},{7,6},{7,8},
						{8,4},{8,7},{8,9},{8,10},{8,11},
						{9,8},{9,11},
						{10,2},{10,4},{10,8},{10,11},
						{11,8},{11,9},{11,10}
				};	
			Graph<City> graph = new UnweightedGraph<>(vertices,edges);
			
			//Create a scene and place it in the stage
			Scene scene = new Scene(new GraphView(graph),750,450);
			primaryStage.setTitle("DisplayUSMap");//set the stage title
			primaryStage.setScene(scene); //Place the scene in the stage
			primaryStage.show();//Display the stage
	}
	
	static class City implements Displayable{
		private int x,y;
		private String name;
		public City(String name,int x, int y ) {
			super();
			this.name = name;
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
}


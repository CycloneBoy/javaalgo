/**
 * 文件名    :FlashText.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月5日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author CycloneBoy
 *
 */
public class FlashText extends Application {
	private String text ="";
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane pane = new StackPane();
		Label label = new Label("Programming is fun");
		pane.getChildren().add(label);
		
/*		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true){
						if(label.getText().trim().length() == 0){
							text = "Welcome";
						}else{
							text = "";
						}
						
						Platform.runLater(new Runnable() {	//Run from JavaFX GUI
							
							@Override
							public void run() {
								label.setText(text);
							}
						});
						
						Thread.sleep(200);
					}
				} catch (InterruptedException e) {
				}
			}
		}).start();*/
		
		new Thread(() -> { //lambda expression
			try{
				while(true){
					if(label.getText().trim().length() == 0){
						text = "Welcome";
					}else{
						text = "";
					}
					Platform.runLater(() -> label.setText(text)); //lambda expression
					Thread.sleep(200);
				}
			}catch (InterruptedException e) {
				
			}
		}).start();
		
		//Create a scene and place it in the stage
		Scene scene = new Scene(pane,200,50); 
		primaryStage.setTitle("FlashText"); //Set the stage title
		primaryStage.setScene(scene);//Place the scene in the stage
		primaryStage.show();//Display the stage
	}
	
	/**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
}

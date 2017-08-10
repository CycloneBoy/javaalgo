/**
 * 文件名    :Client.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter31;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author CycloneBoy
 *
 */
public class Client extends Application{
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;
	
	public static void main(String[] args){
		launch(args);
	}
	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Panel p to hold the label and text field
		BorderPane panForTextField = new BorderPane();
		panForTextField.setPadding(new Insets(5,5,5,5));
		panForTextField.setStyle("-fx-border-color:green");
		panForTextField.setLeft(new Label("Enter a radius: "));
		
		TextField tField = new TextField();
		tField.setAlignment(Pos.BOTTOM_RIGHT);
		panForTextField.setCenter(tField);
		
		BorderPane mainPane = new BorderPane();
		// Text area to display contents
		TextArea ta = new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(panForTextField);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane,450,200);
		primaryStage.setTitle("Client:客户端");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		tField.setOnAction(e ->{
			try {
				// Get the radius from the text stage
				double radius = Double.parseDouble(tField.getText().trim());
				
				// Send the radius to the server
				toServer.writeDouble(radius);
				toServer.flush();
				
				// Get area from the server
				double area = fromServer.readDouble();
				
				// Display to the text area 
				ta.appendText("Radius is " + radius + "\n" 	);
				ta.appendText(" Area receiverd from the server is " +
						area + '\n');
			} catch (IOException ex) {
				ex.printStackTrace();
				System.err.println(ex);
			}
		});
		
		
		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);
			//Socket socket = new Socket("130.254.204.36", 8000);
			//Socket socket = new Socket("drake.Armstrong.edu", 8000);
			
			// Create an input stream to receive data from the server
			fromServer = new  DataInputStream(socket.getInputStream());
			
			// Create an output stream to send data to the server
			toServer  = new DataOutputStream(socket.getOutputStream());
			
		} catch (IOException ex) {
			ta.appendText(ex.toString() + '\n' );
		}
	}
	
}

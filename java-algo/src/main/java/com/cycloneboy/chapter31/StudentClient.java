/**
 * 文件名    :StudentClient.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter31;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author CycloneBoy
 *
 */
public class StudentClient extends Application{
	private TextField tfName = new TextField();
	private TextField tfStreet  =  new TextField();
	private TextField tfCity = new TextField();
	private TextField tfState  =  new TextField();
	private TextField tfZip = new TextField();
	
	private Button btRegister = new Button("Register to the server");
	
	// Host name or ip
	String host = "localhost";
	
	
	/**
	 * 
	 */
	public StudentClient() {
		// TODO Auto-generated constructor stub
	}

	public  static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane  =  new GridPane();
		pane.add(new Label("Name"), 0, 0);
		pane.add(tfName, 1, 0);
		pane.add(new Label("Street"), 0, 1);
		pane.add(tfStreet, 1, 1);
		pane.add(new Label("city"), 0, 2);
		HBox hBox = new HBox(2);
		pane.add(hBox, 1, 2);
		hBox.getChildren().addAll(tfCity,new Label("State"),tfState,
				new Label("Zip"),tfZip);
		pane.add(btRegister, 1, 3);
		GridPane.setHalignment(btRegister, HPos.RIGHT);
		
		pane.setAlignment(Pos.CENTER);
		tfName.setPrefColumnCount(15);
		tfStreet.setPrefColumnCount(15);
		tfCity.setPrefColumnCount(10);
		tfState.setPrefColumnCount(2);
		tfZip.setPrefColumnCount(3);
		
		btRegister.setOnAction(new ButtonListener());
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane,450,200);
		primaryStage.setTitle("StudentClient");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	/** Handle button action */
	private class ButtonListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			try {
				// Estalish connection with the server
				Socket socket = new Socket(host, 8000);
				
				// Create an output stream to server
				ObjectOutputStream toServer = 
						new ObjectOutputStream(socket.getOutputStream());
				
				// Get text filed
				String name = tfName.getText().trim();
				String street = tfStreet.getText().trim();
				String city = tfCity.getText().trim();
				String state = tfState.getText().trim();
				String zip = tfZip.getText().trim();
				
				// Create a Student object and sent to server
				StudentAddress s = 
						new StudentAddress(name, street, city, state, zip);
				toServer.writeObject(s);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
	}
}

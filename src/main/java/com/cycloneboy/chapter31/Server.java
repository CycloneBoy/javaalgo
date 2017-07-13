/**
 * 文件名    :Server.java
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sun.print.resources.serviceui_zh_CN;

/**
 * @author CycloneBoy
 *
 */
public class Server extends Application{
	
	/**
	 * 
	 */
	public Server() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TextArea ta = new TextArea();
		
		Scene scene = new Scene(new ScrollPane(ta),450, 200);
		primaryStage.setTitle("Server:服务器");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() ->
					ta.appendText("Server start at " + new Date() + '\n'));
				
				// Listen for a connection request
				Socket socket = serverSocket.accept();
				
				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(
						socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(
						socket.getOutputStream());
				
				while(true){
					// Receiver radius from the client
					double radius = inputFromClient.readDouble();
					
					// Compute data
					double area = radius * radius * Math.PI;
					
					// Send area back to the client
					
					outputToClient.writeDouble(area);
					
					Platform.runLater(()->{
						ta.appendText("Radius received from client:" +
								radius + '\n');
						ta.appendText("Area is :" + area + '\n');
					});
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
		
	}

}

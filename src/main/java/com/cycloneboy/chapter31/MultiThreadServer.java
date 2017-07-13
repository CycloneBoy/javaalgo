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
import java.net.InetAddress;
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
public class MultiThreadServer extends Application{
	private TextArea ta = new TextArea();
	
	// Number a client
	private int clientNo = 0;
	
	/**
	 * 
	 */
	public MultiThreadServer() {
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
		
		// Create a scene and place it in the stage
		
		Scene scene = new Scene(new ScrollPane(ta),450, 200);
		primaryStage.setTitle("MultiThreadServer:多线程服务器");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() ->
					ta.appendText("MultiThreadServer start at " + new Date() + '\n'));
				
				while(true){
					// Listen for a connection request
					Socket socket = serverSocket.accept();
					
					// Increment clientNo
					clientNo++;
					
					Platform.runLater(() ->{
						// Display the client number
						ta.appendText("Starting thread for client " +
									clientNo + " at " + new Date() + '\n');
						
						// Find the client's host name , and IP address
						InetAddress inetAddress = socket.getInetAddress();
						ta.appendText("Client " + clientNo + " 's host name is " +
								inetAddress.getHostAddress() + '\n');
						ta.appendText("Client " + clientNo + " 's IP Address is " +
								inetAddress.getHostAddress() + '\n');
					});
					
					// Create and start a new thread for the connection
					new Thread(new HandleAClient(socket)).start();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
		
	}
	
	// Define the thread class for handling new conneciton
	class HandleAClient implements Runnable{
		private Socket socket; // A connected socked
		
		
		public HandleAClient(Socket socket) {
			super();
			this.socket = socket;
		}

		/** Run a thread */
		public void run() {
			try {
				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(
						socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(
						socket.getOutputStream());
				
				// Continuously serve the client
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
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

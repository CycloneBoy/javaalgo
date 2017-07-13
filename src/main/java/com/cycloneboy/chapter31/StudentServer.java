/**
 * 文件名    :StudentServer.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter31;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * @author CycloneBoy
 *
 */
public class StudentServer {
	private ObjectOutputStream outputToFile;
	private ObjectInputStream inputFromClient;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new StudentServer();
	}

	
	/**
	 * 
	 */
	public StudentServer() {
		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Server started");
			
			// Create an object output stream
			outputToFile = new ObjectOutputStream(
					new FileOutputStream("student.dat",true));
			
			while(true){
				// Listen for a new connection request
				Socket socket = serverSocket.accept();
				
				// Create an input stream from the socket
				inputFromClient = new ObjectInputStream(
						socket.getInputStream());
				
				// Read from input
				Object object = inputFromClient.readObject();
				
				outputToFile.writeObject(object);
				System.out.println("A new student object is stored");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputFromClient.close();
				outputToFile.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	
	
}

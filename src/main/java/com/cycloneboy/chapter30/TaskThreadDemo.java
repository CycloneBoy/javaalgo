/**
 * 文件名    :TaskThreadDemo.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月5日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

/**
 * @author CycloneBoy
 *
 */
public class TaskThreadDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Create tasks
		Runnable printA = new PrintChar('a', 100);
		Runnable printB = new PrintChar('b', 100);
		Runnable print100 = new PrintNum(100); 
		
		//Create threads
		Thread thread1 = new Thread(printA);
		Thread thread2 = new Thread(printB);
		Thread thread3 = new Thread(print100);
		
		//Start threads
		thread1.start();
		thread2.start();
		thread3.start();
	}

}

//The task for printing a character a specified number of times
class PrintChar implements Runnable{
	private char charToPrint;//The character to print
	private int times;	//The number of times to repeat
	
	/** 
	 * Construct a task with a specified character and number of 
	 * time to print the character
	 */
	public PrintChar(char c,int t) {
		this.charToPrint = c;
		this.times = t;
	}
	
	@Override
	/**
	 * Override the run() method to tell the system
	 * what task to perform
	 */
	public void run() {
		for(int i = 0; i < times; i++){
			System.out.print(charToPrint);
		}
	}
}

//The task class for printing numbers from 1 to n for a given n
class PrintNum  implements Runnable{
	private int lastNum;
	
	/** Construct a task for printing 1,2,....n*/
	public PrintNum(int n){
		this.lastNum = n;
	}

	@Override 
	/**
	 * Tell the method how to run
	 */
	public void run() {
		for (int i = 0; i <= lastNum; i++) {
			System.out.print(" " + i);
		}
		
	}
}
/**
 * 文件名    :ConsumerProducer.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月14日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.text.StyledEditorKit.ItalicAction;

import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * @author CycloneBoy
 *
 */
public class ConsumerProducerUsingBlockingQueue {
	private static ArrayBlockingQueue<Integer> buffer = 
			new  ArrayBlockingQueue<>(2);
	
	/**
	 * 
	 */
	public ConsumerProducerUsingBlockingQueue() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}

	// A task for adding an int to the buffer
	private static class ProducerTask implements Runnable{
		@Override
		public void run() {
			try {
				int i = 1;
				while(true){
					System.out.println(" Producer writes "+ i);
					buffer.put(i++);
					
					Thread.sleep((int)(Math.random() * 1000));
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// A task for reading and deteting an int from buffer
	private static class ConsumerTask implements Runnable{
		@Override
		public void run() {
			try {
				while(true){
					System.out.println("\t\t\t Consumer reads "+ buffer.take());
					
					Thread.sleep((int)(Math.random() * 1000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
}



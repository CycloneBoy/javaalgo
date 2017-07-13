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
public class ConsumerProducer {
	private static Buffer buffer = new Buffer();
	
	/**
	 * 
	 */
	public ConsumerProducer() {
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
					buffer.write(i++);
					
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
					System.out.println("\t\t\t Consumer reads "+ buffer.read());
					
					Thread.sleep((int)(Math.random() * 1000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// An inner class for buffer
	private static class Buffer{
		private static final int CAPACITY = 1; // buffer size
		private LinkedList<Integer> queue = 
				new LinkedList<>();
		
		// Create a new lock
		private static Lock lock = new ReentrantLock();
		
		// Create two conditions
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();
		
		public void write(int value){
			lock.lock(); // Acquire the lock
			try {
				while( queue.size() == CAPACITY){
					System.out.println(" wait for notfull condition");
					notFull.await();
				}
				
				queue.offer(value);
				notEmpty.signalAll(); // Signal notEmpty condition
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			} finally {
				lock.unlock(); // Release the lock
			}
		}
		
		public int read(){
			int value = 0;
			lock.lock();
			try {
				while(queue.isEmpty()){
					System.out.println("\t\t wait for notEmpty condition");
					notEmpty.await();
				}
				
				value  = queue.remove();
				notFull.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
				return value;
			}
		}
	}
	
}



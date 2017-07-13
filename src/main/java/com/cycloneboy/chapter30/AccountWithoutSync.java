/**
 * 文件名    :AccountWithoutSync.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月11日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author CycloneBoy
 *
 */
public class AccountWithoutSync {
	private static Account account = new Account(); 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// Create and launch 100 threads
		for(int i = 0 ; i< 100; i ++){
			executor.execute(new AddAPennyTask());
		}
		
		executor.shutdown();
		// Wait until all task are finished 
		while(!executor.isTerminated()){
			
		}
		
		System.out.println("What is balance?"+ account.getBalance());
		
	}

	// A thread for adding a penny to the account
	private static class AddAPennyTask implements Runnable{
		public void run(){
			/*synchronized(account){	//同步块
				account.deposit(1);
			}*/
			account.deposit(1);  //不使用同步
		}
	}
	
	// An inner class for account
	private static class Account{
		private int balance = 0;
		
		public int getBalance(){
			return balance;
		}
		
		public synchronized void deposit(int amount){
			int newBalance = balance + amount;
			
			// This delay is deliberately added to magnify the 
			// data corruption probeem ande make it easy to see.
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			balance = newBalance;
//			balance = balance + amount;
		}
	}
}


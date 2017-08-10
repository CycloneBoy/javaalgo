/**
 * 文件名    :ThreadCooperation.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月11日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author CycloneBoy
 *
 */
public class ThreadCooperation {
	private static Account account = new Account();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a thread pool with two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		
		executor.shutdown();
		System.out.println("Thread 1\t\t Thread 2\t\t Balance");
	}

	//DepositTask
	public static class DepositTask implements Runnable{
		
		/**
		 *  Keep adding an amount to the account
		 */
		@Override
		public void run() {
			try {
				while(true){
					account.deposit((int)(Math.random() * 10) + 1);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//WithdrawTask
	public static class WithdrawTask implements Runnable{
		/**
		 * Keep subtracting an amount from the account
		 */
		@Override
		public void run() {
			while(true){
				account.withdraw((int)(Math.random() * 10)+1);
			}
		}
	}
	
	// An inner class for account
	public static class Account{
		// Create a new lock
		private static Lock lock = new ReentrantLock();
		
		// Create a condition
		private static Condition newDeposit = lock.newCondition();
		
		private int balance = 0;
		
		public int getBalance(){
			return balance;
		}
		
		//withdraw
		public void withdraw(int amount){
			lock.lock(); // Acquire the lock
			try {
				while (balance < amount) {
					System.out.println("\t\t\tWait for a deposit");
					newDeposit.await();
				}
				
				balance -= amount;
				System.out.println("\t\t\tWithdraw " + amount + 
						"\t\t" + getBalance());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();  // Release the lock
			}
		}
		
		//deposit
		public void deposit(int amount){
			lock.lock();  // Acquire the lock
			try {
				balance += amount;
				System.out.println("Deposit " + amount + 
						"\t\t\t\t\t" + getBalance());
				
				//Signal thread waiting on the conditon
				newDeposit.signalAll();
			}finally {
				lock.unlock();  // Release the lock
			}
		}
	}
}

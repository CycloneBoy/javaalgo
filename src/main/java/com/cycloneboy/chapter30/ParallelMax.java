/**
 * 文件名    :ParallelMax.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月14日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.sun.istack.internal.FinalArrayList;
import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;

/**
 * @author CycloneBoy
 *
 */
public class ParallelMax {

	/**
	 * 
	 */
	public ParallelMax() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int N = 9000000;
		int [] list = new int[N];
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}
		
		long startTime = System.currentTimeMillis();
		System.out.println("\n The maximal number is " + max(list));
		
		long endTime = System.currentTimeMillis();
		System.out.println("The number of processors is " + 
				Runtime.getRuntime().availableProcessors());
		System.out.println("Time is "+ (endTime - startTime) + " milliseconds");
		
	}

	public static int max(int[] list){
		RecursiveTask<Integer> task = new MaxTask(list, 0, list.length);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);
	}
	
	private static class MaxTask extends RecursiveTask<Integer>{
		private final static int THRESHOLD = 100;
		private int[] list;
		private int low;
		private int high;
		
		public MaxTask(int[] list ,int low,int high) {
			this.list = list;
			this.low = low;
			this.high = high;
		}
		
		@Override
		protected Integer compute() {
			if(high - low < THRESHOLD){
				int max = list[0];
				for (int i = low; i < high; i++) {
					if (list[i] > max) {
						max = list[i];
					}
				}
				return new Integer(max);
			}else {
				int mid = (low + high) /2;
				RecursiveTask<Integer> left = new MaxTask(list, low, mid);
				RecursiveTask<Integer> right = new MaxTask(list, mid, high);
				
				left.fork();
				right.fork();
				return new Integer(Math.max(left.join().intValue(),
						right.join().intValue()));
			}
		}
		
	}
}

/**
 * 文件名    :ParallelMergeSort.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月14日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter30;

import com.cycloneboy.chapter23.MergeSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author CycloneBoy
 *
 */
public class ParallelMergeSort {

	/**
	 * 
	 */
	public ParallelMergeSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int SIZE = 7000000;
		int[] list1 = new int[SIZE];
		int[] list2 = new int[SIZE];
		
		for (int i = 0; i < list1.length; i++) {
			list1[i] = list2[i] = (int)(Math.random() * 10000000);
		}

		long startTime = System.currentTimeMillis();
		parallelMergeSort(list1);
		long endTime = System.currentTimeMillis();
		
		System.out.println("\n Parallel time with " +
				Runtime.getRuntime().availableProcessors() +
				" processors is " + (endTime - startTime) + " milliseconds");
		
		startTime = System.currentTimeMillis();
		MergeSort.mergeSort(list2);
        endTime = System.currentTimeMillis();
		
		System.out.println("\n Sequential time is "
				+ (endTime - startTime) + " milliseconds");
		
	}

	private static void parallelMergeSort(int[] list){
		RecursiveAction mainTask = new SortTask(list);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}
	
	private static class SortTask extends RecursiveAction{
		private final int THRESHOLD = 500;
		private int[] list;
		
		public SortTask(int[] list) {
			this.list = list;
		}
		
		@Override
		protected void compute() {
			if (list.length < THRESHOLD) {
				Arrays.sort(list);
			} else {
				// Obtain the first half
				int[] firstHalf = new int[list.length /2];
				System.arraycopy(list, 0, firstHalf, 0, list.length /2);
				
				// Obtain the second half
				int secondHalfLength = list.length - list.length /2;
				int[] secondHalf = new int[secondHalfLength];
				System.arraycopy(list, list.length / 2, secondHalf, 
						0, secondHalfLength);
				
				// Recursively sort the two halves
				invokeAll( new SortTask(firstHalf),
						new SortTask(secondHalf));
				
				// Merge firstHalf with secondHalf into list
				MergeSort.merge(firstHalf, secondHalf, list);
			}
			
		}
		
	}
}

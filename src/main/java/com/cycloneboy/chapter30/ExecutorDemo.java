/**
 * 文件名    :TaskThreadDemo.java
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
public class ExecutorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create  a fixed thread poll with maximum three threads
		//ExecutorService executor = Executors.newFixedThreadPool(3);
		//ExecutorService executor = Executors.newFixedThreadPool(1);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//Submit runnable tasks to the executor
		executor.execute(new PrintChar('a', 100));
		executor.execute(new PrintChar('b', 100));
		executor.execute(new PrintNum(100));
		
		//shut down the executor
		executor.shutdown();
	}

}

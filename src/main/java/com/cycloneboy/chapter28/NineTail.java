/**
 * 
 */
package com.cycloneboy.chapter28;

import java.util.List;
import java.util.Scanner;


/**
 * @author CycloneBoy
 *
 */
public class NineTail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Prompt the user to enter nine coins' Hs and Ts
		System.out.print("Enter the initial nine coins Hs and Ts: ");
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		char[] initialNode = s.toCharArray();
		
		NineTailModel model = new NineTailModel();
		List<Integer> path = model.getShortestPath(NineTailModel.getIndex(initialNode));
		
		System.out.println("The steps to flip the coins are ");
		for (int i = 0; i < path.size(); i++) {
			NineTailModel.printNode(
					NineTailModel.getNode(path.get(i).intValue()));
		}
		
	}

}
